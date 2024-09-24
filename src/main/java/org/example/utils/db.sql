CREATE TABLE client (
                        client_id      SERIAL PRIMARY KEY,
                        name           VARCHAR(255) NOT NULL,
                        address        VARCHAR(255),
                        phone_number   VARCHAR(20),
                        is_professional BOOLEAN
);


CREATE TABLE project (
                         project_id     SERIAL PRIMARY KEY,
                         project_name   VARCHAR(255) NOT NULL,
                         client_id      INTEGER REFERENCES client(client_id),
                         surface_area   NUMERIC(10, 2) NOT NULL,
                         project_state  VARCHAR(50),
                         tva            NUMERIC(5, 2),
                         profit_margin  NUMERIC(5, 2),
                         total_cost     NUMERIC(10, 2),
                         creation_date  DATE DEFAULT CURRENT_DATE,
                         validity_date  DATE,
                         state          VARCHAR(50)
);


CREATE TABLE material (
                          material_id         SERIAL PRIMARY KEY,
                          project_id          INTEGER REFERENCES project(project_id),
                          name                VARCHAR(255) NOT NULL,
                          quantity            NUMERIC(10, 2) NOT NULL,
                          unit_cost           NUMERIC(10, 2) NOT NULL,
                          transport_cost      NUMERIC(10, 2),
                          quality_coefficient NUMERIC(3, 2) CHECK (quality_coefficient >= 1.0),
                          vat_rate            NUMERIC(5, 2)


CREATE TABLE labor (
                       labor_id            SERIAL PRIMARY KEY,
                       project_id          INTEGER REFERENCES project(project_id),
                       type                VARCHAR(255) NOT NULL,
                       hourly_rate         NUMERIC(10, 2) NOT NULL,
                       hours_worked        NUMERIC(10, 2) NOT NULL,
                       productivity_factor NUMERIC(3, 2) CHECK (productivity_factor >= 1.0),
                       vat_rate            NUMERIC(5, 2)
);


CREATE TABLE devis (
                       devis_id      SERIAL PRIMARY KEY,
                       montant_estime NUMERIC(10, 2) NOT NULL,
                       date_emission  DATE NOT NULL,
                       date_validite  DATE NOT NULL,
                       accepte        BOOLEAN DEFAULT FALSE,
                       project_id     INTEGER REFERENCES project(project_id)
);