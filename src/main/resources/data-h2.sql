INSERT INTO master (object_id, reference, status) VALUES
    (1, 'One', 'INITIAL'),
    (2, 'Two', 'INITIAL');

INSERT INTO detail (object_id, master_id, mod_counter) VALUES
    (1, 1, 0),
    (2, 1, 0),
    (3, 1, 0),
    (4, 2, 0),
    (5, 2, 0),
    (6, 2, 0);
