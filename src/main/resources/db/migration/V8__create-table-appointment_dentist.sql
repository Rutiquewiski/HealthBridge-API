CREATE TABLE appointment_dentist (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    dentist_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    date DATETIME NOT NULL,
    FOREIGN KEY (dentist_id) REFERENCES Dentist(id),
    FOREIGN KEY (patient_id) REFERENCES Patient(id)
);