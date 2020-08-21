CREATE TABLE "public".patient (
  id SERIAL PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  gender SMALLINT NOT NULL,
  date_birth DATE NOT NULL,
  address VARCHAR(1000) NOT NULL,
  oms INT
);
