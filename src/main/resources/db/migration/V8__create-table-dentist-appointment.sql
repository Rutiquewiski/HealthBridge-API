CREATE TABLE dentist_appointment (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    dentist_id BIGINT NOT NULL,
    pacient_id BIGINT NOT NULL,
    date DATETIME NOT NULL,
    canceled TINYINT(1),
    FOREIGN KEY (dentist_id) REFERENCES Dentist(id),
    FOREIGN KEY (pacient_id) REFERENCES Pacient(id)
);