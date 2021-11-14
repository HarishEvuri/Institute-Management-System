CREATE TABLE IF NOT EXISTS User
(
    username VARCHAR(50) UNIQUE NOT NULL,
    passwordHash VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    emailAddress VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    gender enum('Male','Female','Not Specified') NOT NULL DEFAULT 'Not Specified',
    address VARCHAR(255) NOT NULL,
    lastLoginTime TIMESTAMP DEFAULT NULL,
    role enum('Admin','Student','Professor', 'Staff') NOT NULL,
    token VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS Department
(
    departmentId VARCHAR(50) UNIQUE NOT NULL,
    departmentName VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (departmentId)
);

CREATE TABLE IF NOT EXISTS UserPhoneNumber
(
    username VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE,
    PRIMARY KEY (username, phoneNumber)
);

CREATE TABLE IF NOT EXISTS Student
(
    rollNumber VARCHAR(50) UNIQUE NOT NULL,
    joinYear INT NOT NULL,
    caste enum('OC', 'OBC', 'SC', 'ST') NOT NULL,
    familyIncome enum('<1 lac', '1 lac - 5 lacs', '>5 lacs') NOT NULL,
    gaurdianName VARCHAR(255) NOT NULL,
    gaurdianRelation enum('Father', 'Mother', 'Brother', 'Sister', 'Other') NOT NULL,
    gaurdianPhoneNumber VARCHAR(50) NOT NULL,
    username VARCHAR(255) NOT NULL,
    departmentId VARCHAR(50),
    FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE,
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId) ON DELETE SET NULL,
    PRIMARY KEY (rollNumber)
);

CREATE TABLE IF NOT EXISTS FeePayment
(
    transactionId VARCHAR(255) UNIQUE NOT NULL,
    rollNumber VARCHAR(50) NOT NULL,
    transactionDate DATE NOT NULL,
    transactionTime TIME NOT NULL,
    semester enum('odd','even') NOT NULL,
    year INT NOT NULL,
    amount INT NOT NULL,
    modeOfPayment enum('Online', 'DD'),
    FOREIGN KEY (rollNumber) REFERENCES Student(rollNumber) ON DELETE CASCADE,
    PRIMARY KEY (transactionId)
);

CREATE TABLE IF NOT EXISTS Employee
(
    employeeId VARCHAR(50) UNIQUE NOT NULL,
    joinDate DATE NOT NULL,
    endDate DATE,
    accountNumber VARCHAR(50) NOT NULL,
    bank_IFSC_code VARCHAR(50) NOT NULL,
    panNumber VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE,
    PRIMARY KEY (employeeId)
);

CREATE TABLE IF NOT EXISTS SalaryPayment
(
    transactionId VARCHAR(255) UNIQUE NOT NULL,
    employeeId VARCHAR(50) NOT NULL,
    transactionDate DATE NOT NULL,
    transactionTime TIME NOT NULL,
    month VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    amount INT NOT NULL,
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE,
    PRIMARY KEY (transactionId)
);

CREATE TABLE IF NOT EXISTS Staff
(
    staffId VARCHAR(50) UNIQUE NOT NULL,
    designation VARCHAR(50) NOT NULL,
    employeeId VARCHAR(50) NOT NULL,
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE,
    PRIMARY KEY (staffId)
);

CREATE TABLE IF NOT EXISTS Professor
(
    professorId VARCHAR(50) UNIQUE NOT NULL,
    qualification VARCHAR(255) NOT NULL,
    employeeId VARCHAR(50) NOT NULL,
    departmentId VARCHAR(50),
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE,
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId) ON DELETE SET NULL,
    PRIMARY KEY (professorId)
);

CREATE TABLE IF NOT EXISTS HOD
(
    departmentId VARCHAR(50) NOT NULL,
    professorId VARCHAR(50) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE DEFAULT NULL,
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId) ON DELETE CASCADE,
    FOREIGN KEY (professorId) REFERENCES Professor(professorId) ON DELETE CASCADE,
    PRIMARY KEY (departmentId, professorId, startDate)
);

CREATE TABLE IF NOT EXISTS Course
(
    courseId VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    credits INT NOT NULL,
    departmentId VARCHAR(50) NOT NULL,
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId) ON DELETE CASCADE,
    PRIMARY KEY (courseId)
);

CREATE TABLE IF NOT EXISTS Section
(
    sectionId VARCHAR(50) UNIQUE NOT NULL,
    courseId VARCHAR(50) NOT NULL,
    semester enum('odd','even') NOT NULL,
    year INT NOT NULL,
    roomNumber VARCHAR(50) NOT NULL,
    professorId VARCHAR(50),
    isLocked BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (courseId) REFERENCES Course(courseId) ON DELETE CASCADE,
    FOREIGN KEY (professorId) REFERENCES Professor(professorId) ON DELETE SET NULL,
    PRIMARY KEY (sectionId, courseId)
);

CREATE TABLE IF NOT EXISTS SectionTiming
(
    sectionId VARCHAR(50) NOT NULL,
    courseId VARCHAR(50) NOT NULL,
    day enum('MON', 'TUE', 'WED', 'THU', 'FRI') NOT NULL,
    slot enum('8:00 AM - 9:00 AM', 
                '9:00 AM - 10:00 AM', 
                '10:00 AM - 11:00 AM', 
                '11:00 AM - 12:00 PM',
                '1:00 PM - 2:00 PM',
                '2:00 PM - 3:00 PM',
                '3:00 PM - 4:00 PM',
                '4:00 PM - 5:00 PM' 
                ) NOT NULL,
    FOREIGN KEY (sectionId, courseId) REFERENCES Section(sectionId, courseId) ON DELETE CASCADE,
    PRIMARY KEY (sectionId, courseId, day, slot)
);

CREATE TABLE IF NOT EXISTS Enrollment
(
    rollNumber VARCHAR(50) NOT NULL,
    sectionId VARCHAR(50) NOT NULL,
    courseId VARCHAR(50) NOT NULL,
    grade enum('A','A-','B','B-','C','C-','F','Not Graded') NOT NULL DEFAULT 'Not Graded',
    attendance INT NOT NULL DEFAULT 0,
    FOREIGN KEY (rollNumber) REFERENCES Student(rollNumber) ON DELETE CASCADE,
    FOREIGN KEY (sectionId, courseId) REFERENCES Section(sectionId, courseId) ON DELETE CASCADE,
    PRIMARY KEY (rollNumber, sectionId, courseId)
);

CREATE TABLE IF NOT EXISTS Complaint
(
    complaintId INT NOT NULL AUTO_INCREMENT,
    rollNumber VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    subject VARCHAR(50) NOT NULL,
    description VARCHAR(1000),
    response VARCHAR(1000),
    FOREIGN KEY (rollNumber) REFERENCES Student(rollNumber) ON DELETE CASCADE,
    PRIMARY KEY (complaintId)
);


DELIMITER $$
CREATE TRIGGER check_insert_employee_panNumber BEFORE INSERT ON Employee
FOR EACH ROW
BEGIN
IF (NEW.panNumber REGEXP '^[A-Z]{5}[0-9]{4}[A-Z]{1}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong PAN Number Format';
END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER check_update_employee_panNumber BEFORE UPDATE ON Employee
FOR EACH ROW
BEGIN
IF (NEW.panNumber REGEXP '^[A-Z]{5}[0-9]{4}[A-Z]{1}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong PAN Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_insert_employee_IFSC BEFORE INSERT ON Employee
FOR EACH ROW
BEGIN
IF (NEW.bank_IFSC_code REGEXP '^[A-Z]{4}[0-9]{7}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong IFSC Code Format';
END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER check_update_employee_IFSC BEFORE UPDATE ON Employee
FOR EACH ROW
BEGIN
IF (NEW.bank_IFSC_code REGEXP '^[A-Z]{4}[0-9]{7}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong IFSC Code Format';
END IF;
END$$
DELIMITER ;