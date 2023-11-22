CREATE TABLE appointment_doctor (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    date DATETIME NOT NULL,
    canceled TINYINT(1),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(id),
    FOREIGN KEY (patient_id) REFERENCES Patient(id)
);