DROP TABLE IF EXISTS detail;
DROP TABLE IF EXISTS master;

CREATE TABLE master (
    object_id INT PRIMARY KEY NOT NULL,
    reference VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE detail (
    object_id INT PRIMARY KEY NOT NULL,
    master_id INT NOT NULL,
    mod_counter INT NOT NULL,
    CONSTRAINT fk_detail_master FOREIGN KEY (master_id) REFERENCES master(object_id)
);