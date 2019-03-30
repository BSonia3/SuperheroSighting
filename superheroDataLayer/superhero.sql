DROP DATABASE IF EXISTS superherosightings ;

CREATE DATABASE IF NOT EXISTS superherosightings;

USE  superherosightings ;

CREATE TABLE IF NOT EXISTS superpower(
   superpower_id int NOT NULL auto_increment,
   superpower varchar(20),
   PRIMARY KEY(superpower_id)
);

CREATE TABLE IF NOT EXISTS superhero
(superhero_id int NOT NULL auto_increment,
superhero_name varchar(45)NOT NULL,
superhero_descreption varchar(45),
superpower_id int NOT NULL,
PRIMARY KEY(superhero_id));


-- Create the foreign key relationships
ALTER TABLE superhero ADD CONSTRAINT fk_superhero_superpower
FOREIGN KEY (superpower_id ) REFERENCES superpower(superpower_id);


CREATE TABLE IF NOT EXISTS organisation
(organisation_id int NOT NULL auto_increment,
org_name varchar(45)NOT NULL,
org_address varchar(45),
org_city varchar(20),
org_state varchar(10),
org_zipcode varchar(10),
org_contact varchar(20),
PRIMARY KEY(organisation_id));


CREATE TABLE IF NOT EXISTS superhero_organisation
( superhero_id int NOT NULL,
organisation_id int NOT NULL);

-- Create the foreign key relationships
ALTER TABLE superhero_organisation ADD CONSTRAINT fk_superhero_organisation_superhero
FOREIGN KEY (superhero_id ) REFERENCES superhero(superhero_id);
ALTER TABLE superhero_organisation ADD CONSTRAINT fk_superhero_organisation_organisation
FOREIGN KEY (organisation_id ) REFERENCES organisation(organisation_id);

CREATE TABLE IF NOT EXISTS location
( location_id int NOT NULL auto_increment,
loc_name varchar(45)NOT NULL,
loc_descreption varchar(45),
loc_address varchar(45),
loc_city varchar(20),
loc_state varchar(10),
loc_zipcode varchar(10),
longitude decimal(9,7),
latitude decimal(9,7),
PRIMARY KEY( location_id));

CREATE TABLE IF NOT EXISTS sighting
( sighting_id  int NOT NULL auto_increment,
sighting_date date,
location_id int NOT NULL,
PRIMARY KEY(sighting_id));

ALTER TABLE sighting ADD CONSTRAINT fk_sighting_location
FOREIGN KEY (location_id) REFERENCES location(location_id);

CREATE TABLE IF NOT EXISTS superhero_sighting
( 
  sighting_id  int NOT NULL,
  superhero_id int NOT NULL
 );

-- Create the foreign key relationships
ALTER TABLE superhero_sighting ADD CONSTRAINT fk_superhero_sighting_superhero
FOREIGN KEY (superhero_id ) REFERENCES superhero(superhero_id);

ALTER TABLE superhero_sighting ADD CONSTRAINT fk_superhero_sighting_sighting
FOREIGN KEY (sighting_id) REFERENCES sighting(sighting_id);
