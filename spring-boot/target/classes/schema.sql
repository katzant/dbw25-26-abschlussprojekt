CREATE TABLE adresse (
    adresse_id SERIAL PRIMARY KEY,
    aktiv BOOLEAN NOT NULL,
    strasse VARCHAR(60) NOT NULL CHECK (strasse ~ '^[A-ZÄÜÖ][a-züäöß]*$'),
    hausnummer VARCHAR(15) NOT NULL CHECK (hausnummer ~ '^[0-9]+[a-z]?$'),
    plz BIGINT NOT NULL CHECK (plz > 0 AND plz <= 999999999999),
    ort VARCHAR(100) NOT NULL,
    land VARCHAR(100) NOT NULL
);

CREATE TABLE kunde (
    kunde_id SERIAL PRIMARY KEY,
    email VARCHAR(256) NOT NULL UNIQUE CHECK (email ~ '[@][-a-zA-Z0-9]+[.]'),
    passwort VARCHAR(20) NOT NULL CHECK (
        LENGTH(passwort) BETWEEN 5 AND 20
        AND (passwort ~ '[a-zA-Z]')
        AND (passwort ~ '[0-9]')
        AND (passwort ~ '[^a-zA-Z0-9]')),
    vorname VARCHAR(50) NOT NULL,
    nachname VARCHAR(50) NOT NULL
);

CREATE TABLE kunde_hat_adressen (
    adresse_id INTEGER NOT NULL,
    kunde_id INTEGER NOT NULL,
    typ VARCHAR(30) NOT NULL CHECK (typ = 'Lieferadresse' OR typ = 'Rechnungsadresse'),
    PRIMARY KEY (adresse_id, kunde_id),
    FOREIGN KEY (adresse_id) REFERENCES adresse(adresse_id) ON DELETE CASCADE,
    FOREIGN KEY (kunde_id) REFERENCES kunde(kunde_id) ON DELETE CASCADE
);

CREATE TABLE mitarbeiter (
    personal_nr SERIAL PRIMARY KEY,
    passwort VARCHAR(20) NOT NULL CHECK (
        LENGTH(passwort) BETWEEN 5 AND 20
            AND (passwort ~ '[a-zA-Z]')
            AND (passwort ~ '[0-9]')
            AND (passwort ~ '[^a-zA-Z0-9]')),
    email VARCHAR(100) NOT NULL,
    vorname VARCHAR(100) NOT NULL,
    nachname VARCHAR(100) NOT NULL
);

CREATE TABLE bestellung (
    status VARCHAR(30) NOT NULL CHECK (
        status = 'neu'
        OR status = 'bezahlt'
        OR status = 'versendet'
        OR status = 'abgeschlossen'
        OR status = 'storniert'
        ),
    datum TIMESTAMP NOT NULL,
    bestellung_id SERIAL PRIMARY KEY,
    mitarbeiterzuweis INTEGER NOT NULL,
    kunde_id INTEGER NOT NULL,
    FOREIGN KEY (mitarbeiterzuweis) REFERENCES mitarbeiter(personal_nr) ON DELETE CASCADE,
    FOREIGN KEY (kunde_id) REFERENCES kunde(kunde_id) ON DELETE CASCADE
);

CREATE FUNCTION status_storniert_exception() RETURNS TRIGGER LANGUAGE plpgsql AS $$
BEGIN
    RAISE EXCEPTION 'Error: Bestellung darf nur storniert werden, wenn status vorher "neu" oder "bezahlt" war';
END;
$$;

CREATE TRIGGER status_storniert_trigger
BEFORE UPDATE OF status ON bestellung
FOR EACH ROW
WHEN (NEW.status = 'storniert' AND OLD.status NOT IN ('neu', 'bezahlt'))
EXECUTE FUNCTION status_storniert_exception();

CREATE TABLE produkt (
    lagerbestand INTEGER NOT NULL CHECK (0 <= lagerbestand),
    sku VARCHAR(100) PRIMARY KEY UNIQUE,
    name VARCHAR (200) NOT NULL,
    preis DECIMAL(10,2) NOT NULL,
    angelegt_von INTEGER NOT NULL,
    FOREIGN KEY (angelegt_von) REFERENCES mitarbeiter(personal_nr) ON DELETE CASCADE
);


CREATE TABLE bestellposition (
    menge INTEGER NOT NULL CHECK (0 < menge),
    bestellung_id INTEGER NOT NULL,
    FOREIGN KEY (bestellung_id) REFERENCES bestellung(bestellung_id) ON DELETE CASCADE,
    position_id SERIAL PRIMARY KEY,
    sku VARCHAR(200) NOT NULL,
    FOREIGN KEY (sku) REFERENCES produkt(sku) ON DELETE CASCADE
);

CREATE FUNCTION insert_bestellposition() RETURNS TRIGGER LANGUAGE plpgsql AS $$
    BEGIN
        IF (SELECT status FROM bestellung WHERE bestellung_id = NEW.bestellung_id)= 'storniert'
            THEN RAISE EXCEPTION 'Error: Bestellung ist storniert und nicht mehr gültig';
        END IF;
        IF (SELECT lagerbestand FROM produkt WHERE sku = NEW.sku) < NEW.menge
            THEN RAISE EXCEPTION 'Error: Lagerbestand nicht ausreichend für bestellte Menge';
        ELSE UPDATE produkt SET lagerbestand = lagerbestand - NEW.menge WHERE sku = NEW.sku;
        END IF;
        RETURN NEW;
    END;
$$;

CREATE TRIGGER insert_bestellposition_trigger
BEFORE INSERT ON bestellposition
FOR EACH ROW
EXECUTE FUNCTION insert_bestellposition();


CREATE FUNCTION update_menge_bestellposition() RETURNS TRIGGER LANGUAGE plpgsql AS $$
    BEGIN
        IF (SELECT status FROM bestellung WHERE bestellung_id = NEW.bestellung_id)= 'storniert'
        THEN RAISE EXCEPTION 'Error: Bestellung ist storniert und nicht mehr gültig';
        END IF;
        IF NEW.menge > OLD.menge AND
           ((SELECT lagerbestand FROM produkt WHERE sku = NEW.sku) < (NEW.menge - OLD.menge))
           THEN RAISE EXCEPTION 'Error: Lagerbestand reicht für Mengenerhöhung nicht aus';
        ELSE
            UPDATE produkt SET lagerbestand = lagerbestand - (NEW.menge - OLD.menge) WHERE sku = NEW.sku;
        END IF;
        RETURN NEW;
    END;
$$;

CREATE TRIGGER update_menge_bestellposition_trigger
BEFORE UPDATE OF menge ON bestellposition
FOR EACH ROW
EXECUTE FUNCTION update_menge_bestellposition();


CREATE FUNCTION delete_bestellposition() RETURNS TRIGGER LANGUAGE plpgsql AS $$
   BEGIN
       IF (SELECT status FROM bestellung WHERE bestellung_id = NEW.bestellung_id)= 'storniert'
       THEN RAISE EXCEPTION 'Error: Bestellung ist storniert und nicht mehr gültig';
       END IF;
       UPDATE produkt SET lagerbestand = lagerbestand + OLD.menge WHERE sku = OLD.sku;
       RETURN OLD;
   END;
$$;

CREATE TRIGGER delete_bestellposition_trigger
BEFORE DELETE ON bestellposition
FOR EACH ROW
EXECUTE FUNCTION delete_bestellposition();


CREATE FUNCTION storniert_produkte_zurueckstellen() RETURNS TRIGGER LANGUAGE plpgsql AS $$
    BEGIN
        -- bestellung (bestellung id)-> bestellposition (sku) -> produkt
        UPDATE produkt SET lagerbestand = lagerbestand + bestellposition.menge
            FROM bestellposition WHERE produkt.sku = bestellposition.sku AND
                                       bestellposition.bestellung_id = NEW.bestellung_id;
        RETURN NEW;
    END;
$$;

CREATE TRIGGER storniert_produkte_zurueckstellen_trigger
AFTER UPDATE OF status ON bestellung
FOR EACH ROW
WHEN(NEW.status = 'storniert' AND OLD.status != 'storniert')
EXECUTE FUNCTION storniert_produkte_zurueckstellen();





-----------------
-- Aufgabe 5




