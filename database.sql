CREATE TABLE IF NOT EXISTS User
(
    username VARCHAR(50) UNIQUE NOT NULL,
    passwordHash VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255),
    dateOfBirth DATE NOT NULL,
    gender enum('Male','Female','Not Specified') NOT NULL DEFAULT 'Not Specified',
    address VARCHAR(255) NOT NULL,
    lastLoginTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role enum('Admin','Student','Professor', 'Staff') NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS Department
(
    departmentId VARCHAR(50) UNIQUE NOT NULL,
    departmentName VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (departmentId)
);

CREATE TABLE IF NOT EXISTS Fee
(
    feeId INT NOT NULL AUTO_INCREMENT,
    semester enum('odd','even') NOT NULL,
    incomeSlab enum('<1 lac', '1 lac - 5 lacs', '>5 lacs') NOT NULL,
    caste enum('OC', 'OBC', 'SC', 'ST') NOT NULL,
    amount INT NOT NULL,
    PRIMARY KEY (feeId)
);

CREATE TABLE IF NOT EXISTS Salary
(
    salaryId INT NOT NULL AUTO_INCREMENT,
    role enum('professor', 'staff') NOT NULL,
    experience INT NOT NULL,
    amount INT NOT NULL,
    PRIMARY KEY (salaryId)
);

CREATE TABLE IF NOT EXISTS UserPhoneNumber
(
    username VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES User(username),
    PRIMARY KEY (username, phoneNumber)
);

CREATE TABLE IF NOT EXISTS UserEmail
(
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES User(username),
    PRIMARY KEY (username, email)
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
    departmentId VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES User(username),
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId),
    PRIMARY KEY (rollNumber)
);

CREATE TABLE IF NOT EXISTS FeePayment
(
    transactionId VARCHAR(255) UNIQUE NOT NULL,
    rollNumber VARCHAR(50) NOT NULL,
    feeId INT NOT NULL,
    transactionTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modeOfPayment enum('Online Payment', 'DD'),
    FOREIGN KEY (rollNumber) REFERENCES Student(rollNumber),
    FOREIGN KEY (feeId) REFERENCES Fee(feeId),
    PRIMARY KEY (transactionId)
);

CREATE TABLE IF NOT EXISTS Employee
(
    employeeId VARCHAR(50) UNIQUE NOT NULL,
    joinDate DATE NOT NULL,
    endDate DATE NOT NULL,
    accountNumber VARCHAR(50) NOT NULL,
    bank_IFSC_code VARCHAR(50) NOT NULL,
    panNumber VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES User(username),
    PRIMARY KEY (employeeId)
);

CREATE TABLE IF NOT EXISTS SalaryPayment
(
    transactionId VARCHAR(255) UNIQUE NOT NULL,
    transactionTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    employeeId VARCHAR(50) NOT NULL,
    salaryId INT NOT NULL,
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId),
    FOREIGN KEY (salaryId) REFERENCES Salary(salaryId),
    PRIMARY KEY (transactionId)
);

CREATE TABLE IF NOT EXISTS Staff
(
    staffId VARCHAR(50) UNIQUE NOT NULL,
    designation VARCHAR(50) NOT NULL,
    employeeId VARCHAR(50) NOT NULL,
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId),
    PRIMARY KEY (staffId)
);

CREATE TABLE IF NOT EXISTS Professor
(
    professorId VARCHAR(50) UNIQUE NOT NULL,
    qualification VARCHAR(255) NOT NULL,
    employeeId VARCHAR(50) NOT NULL,
    departmentId VARCHAR(50) NOT NULL,
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId),
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId),
    PRIMARY KEY (professorId)
);

CREATE TABLE IF NOT EXISTS HOD
(
    departmentId VARCHAR(50) NOT NULL,
    professorId VARCHAR(50) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE DEFAULT NULL,
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId),
    FOREIGN KEY (professorId) REFERENCES Professor(professorId),
    PRIMARY KEY (departmentId, professorId, startDate)
);

CREATE TABLE IF NOT EXISTS Course
(
    courseId VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    credits INT NOT NULL,
    departmentId VARCHAR(50) NOT NULL,
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId),
    PRIMARY KEY (courseId)
);

CREATE TABLE IF NOT EXISTS Section
(
    sectionId VARCHAR(50) UNIQUE NOT NULL,
    courseId VARCHAR(50) NOT NULL,
    semester enum('odd','even') NOT NULL,
    year INT NOT NULL,
    roomNumber VARCHAR(50) NOT NULL,
    professorId VARCHAR(50) NOT NULL,
    isLocked BOOLEAN NOT NULL DEFAULT VALUE false,
    FOREIGN KEY (courseId) REFERENCES Course(courseId),
    FOREIGN KEY (professorId) REFERENCES Professor(professorId),
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
    FOREIGN KEY (sectionId, courseId) REFERENCES Section(sectionId, courseId),
    PRIMARY KEY (sectionId, courseId, day, slot)
);

CREATE TABLE IF NOT EXISTS Enrollment
(
    rollNumber VARCHAR(50) NOT NULL,
    sectionId VARCHAR(50) NOT NULL,
    courseId VARCHAR(50) NOT NULL,
    grade enum('A','A-','B','B-','C','C-','F','Not Graded') NOT NULL DEFAULT 'Not Graded',
    attendance INT DEFAULT NULL,
    feedback VARCHAR(500) DEFAULT NULL,
    FOREIGN KEY (rollNumber) REFERENCES Student(rollNumber),
    FOREIGN KEY (sectionId, courseId) REFERENCES Section(sectionId, courseId),
    PRIMARY KEY (rollNumber, sectionId, courseId)
)
