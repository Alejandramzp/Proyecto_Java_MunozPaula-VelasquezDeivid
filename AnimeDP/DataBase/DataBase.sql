-- Active: 1724785416376@@bm8yuvtf6fnhrm8sbkew-mysql.services.clever-cloud.com@3306
create database bm8yuvtf6fnhrm8sbkew;

use bm8yuvtf6fnhrm8sbkew;

CREATE TABLE Event (
    EventID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) UNIQUE,
    Country VARCHAR(50),
    City VARCHAR(50),
    Address VARCHAR(100),
    MaxPersonCapacity INT,
    MaxStoreCapacity INT,
    MaxRestaurantCapacity INT,
    Date DATE,
    Time TIME,
    Organizer VARCHAR(100),
    AgeRating VARCHAR(20),
    Status ENUM('Activo', 'Completado', 'Pendiente')
);

select * from Event;

CREATE TABLE ActivityRole (
    ActivityRoleID INT AUTO_INCREMENT PRIMARY KEY,
    ActivityName VARCHAR(100)
);

CREATE TABLE Role (
    RoleID INT AUTO_INCREMENT PRIMARY KEY,
    RoleName VARCHAR(100),
    activity1ID INT,
    activity2ID INT,
    FOREIGN KEY (activity1ID) REFERENCES ActivityRole(ActivityRoleID),
    FOREIGN KEY (activity2ID) REFERENCES ActivityRole(ActivityRoleID)
);

CREATE TABLE Staff (
    StaffID INT AUTO_INCREMENT PRIMARY KEY,
    EventID INT,
    Name VARCHAR(100),
    Identification VARCHAR(50),
    DateOfBirth DATE,
    RoleID INT,
    Status ENUM('Trabajo Asignado', 'No trabajando', 'Despedido', 'Incapacitado'),
    FOREIGN KEY (EventID) REFERENCES Event(EventID),
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

CREATE TABLE Props (
    PropID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    Quantity INT,
    Status ENUM('En almacen', 'En sitio'),
    EventID INT,
    FOREIGN KEY (EventID) REFERENCES Event(EventID)
);

CREATE TABLE TicketOffice (
    TicketOfficeID INT AUTO_INCREMENT PRIMARY KEY,
    EventID INT,
    Location VARCHAR(100),
    ContactNumber VARCHAR(15),
    StaffInChargeID INT,
    FOREIGN KEY (EventID) REFERENCES Event(EventID),
    FOREIGN KEY (StaffInChargeID) REFERENCES Staff(StaffID)
);

CREATE TABLE Visitor (
    VisitorID INT AUTO_INCREMENT PRIMARY KEY,
    TicketID INT,
    Name VARCHAR(100),
    IdentificationDocument VARCHAR(50),
    Gender ENUM('Hombre', 'Mujer', 'Otros'),
    DateOfBirth DATE,
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15),
    Status ENUM('Participa', 'No Participa', 'Ganador')
);

CREATE TABLE Ticket (
    TicketID INT AUTO_INCREMENT PRIMARY KEY,
    TicketName VARCHAR(100),
    Price DECIMAL(10, 2),
    AgeRating VARCHAR(20),
    AdditionalCost DECIMAL(10, 2),
    Status ENUM('Pagado', 'Reservado'),
    VisitorID INT,
    TicketOfficeID INT,
    FOREIGN KEY (VisitorID) REFERENCES Visitor(VisitorID),
    FOREIGN KEY (TicketOfficeID) REFERENCES TicketOffice(TicketOfficeID)
);

CREATE TABLE Category (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    CategoryName VARCHAR(100),
    Age INT,
    Gender ENUM('Hombre', 'Mujeres')
);

CREATE TABLE Activity (
    ActivityID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    Type ENUM('Cosplay', 'Trivia'),
    CategoryID INT,
    NumberOfParticipants INT,
    EventID INT,
    StartTime TIME,
    StaffID INT,
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID),
    FOREIGN KEY (EventID) REFERENCES Event(EventID),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
);

CREATE TABLE ActivityParticipation (
    ParticipationID INT AUTO_INCREMENT PRIMARY KEY,
    VisitorID INT,
    ActivityID INT,
    FOREIGN KEY (VisitorID) REFERENCES Visitor(VisitorID),
    FOREIGN KEY (ActivityID) REFERENCES Activity(ActivityID)
);

CREATE TABLE EventAccounting (
    AccountingID INT AUTO_INCREMENT,
    EventID INT,
    ActivityID INT,
    TicketsSold INT,
    ActivityParticipation INT,
    TotalAmount DECIMAL(10, 2),
    PRIMARY KEY (AccountingID, EventID, ActivityID),
    FOREIGN KEY (EventID) REFERENCES Event(EventID),
    FOREIGN KEY (ActivityID) REFERENCES Activity(ActivityID)
);

CREATE TABLE Business (
    BusinessID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    Type ENUM('Tienda', 'Restaurante'),
    InChargeID INT,
    FOREIGN KEY (InChargeID) REFERENCES Staff(StaffID)
);

CREATE TABLE Prize (
    PrizeID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(50),
    Description VARCHAR(255),
    Value DECIMAL(10, 2),
    Status ENUM('Disponible', 'Entregado'),
    ActivityID INT,
    VisitorID INT,
    BusinessID INT,
    FOREIGN KEY (ActivityID) REFERENCES Activity(ActivityID),
    FOREIGN KEY (VisitorID) REFERENCES Visitor(VisitorID),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID)
);

CREATE TABLE CosplayContest (
    CosplayContestID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    CategoryID INT,
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

CREATE TABLE CosplayParticipant (
    ParticipationID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    Score DECIMAL(5, 2),
    CosplayContestID INT,
    FOREIGN KEY (ParticipationID) REFERENCES ActivityParticipation(ParticipationID),
    FOREIGN KEY (CosplayContestID) REFERENCES CosplayContest(CosplayContestID)
);

CREATE TABLE TriviaContest (
    TriviaContestID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    CategoryID INT,
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

CREATE TABLE TriviaQuestion (
    QuestionID INT AUTO_INCREMENT PRIMARY KEY,
    Question VARCHAR(255),
    CorrectAnswer VARCHAR(255),
    Category VARCHAR(100),
    Difficulty ENUM('Facil', 'Intermedio', 'Dificil')
);

CREATE TABLE TriviaParticipant (
    ParticipationID INT AUTO_INCREMENT PRIMARY KEY,
    Score DECIMAL(5, 2),
    TriviaContestID INT,
    FOREIGN KEY (ParticipationID) REFERENCES ActivityParticipation(ParticipationID),
    FOREIGN KEY (TriviaContestID) REFERENCES TriviaContest(TriviaContestID)
);

CREATE TABLE Question_Participant (
    QuestionID INT,
    ParticipationID INT,
    PRIMARY KEY (QuestionID, ParticipationID),
    FOREIGN KEY (QuestionID) REFERENCES TriviaQuestion(QuestionID),
    FOREIGN KEY (ParticipationID) REFERENCES TriviaParticipant(ParticipationID)
);

CREATE TABLE BusinessStaff (
    StaffID INT,
    BusinessID INT,
    PRIMARY KEY (StaffID, BusinessID),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID)
);

CREATE TABLE CashRegister (
    CashRegisterID INT AUTO_INCREMENT PRIMARY KEY,
    Status ENUM('Activo', 'Inactivo'),
    OpeningAmount DECIMAL(10, 2),
    ClosingAmount DECIMAL(10, 2),
    BusinessStaffID INT,
    FOREIGN KEY (BusinessStaffID) REFERENCES BusinessStaff(StaffID)
);

CREATE TABLE orderr (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    VisitorID INT,
    BusinessID INT,
    CashRegisterID INT,
    TotalValue DECIMAL(10, 2),
    Status ENUM('Registrado', 'Pagado', 'Entregado'),
    FOREIGN KEY (VisitorID) REFERENCES Visitor(VisitorID),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID),
    FOREIGN KEY (CashRegisterID) REFERENCES CashRegister(CashRegisterID)
);

CREATE TABLE OrderItem (
    OrderItemID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    ItemName VARCHAR(100),
    Quantity INT,
    IndividualValue DECIMAL(10, 2),
    FOREIGN KEY (OrderID) REFERENCES orderr(OrderID)
);

CREATE TABLE StoreInventory (
    InventoryID INT AUTO_INCREMENT PRIMARY KEY,
    BusinessID INT,
    ProductName VARCHAR(100),
    Description VARCHAR(255),
    Manufacturer VARCHAR(100),
    Type VARCHAR(50),
    AvailableQuantity INT,
    IndividualPrice DECIMAL(10, 2),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID)
);

CREATE TABLE DiscountPromotion (
    DiscountID INT AUTO_INCREMENT PRIMARY KEY,
    InventoryID INT,
    Description VARCHAR(255),
    Type VARCHAR(50),
    DiscountValue DECIMAL(10, 2),
    FOREIGN KEY (InventoryID) REFERENCES StoreInventory(InventoryID)
);

CREATE TABLE Dish (
    DishID INT AUTO_INCREMENT PRIMARY KEY,
    Description VARCHAR(255),
    Type ENUM('Entrada', 'Bebida', 'Plato Fuerte', 'Aperitivo'),
    PreparationTimeMinutes INT
);

CREATE TABLE RestaurantMenu (
    BusinessID INT,
    DishID INT,
    PRIMARY KEY (BusinessID, DishID),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID),
    FOREIGN KEY (DishID) REFERENCES Dish(DishID)
);

CREATE TABLE IngredientInventory (
    IngredientInventoryID INT AUTO_INCREMENT PRIMARY KEY,
    BusinessID INT,
    IngredientName VARCHAR(100),
    AvailableQuantity DECIMAL(10, 2),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID)
);

CREATE TABLE DishIngredient (
    DishID INT,
    IngredientID INT,
    IngredientQuantity DECIMAL(10, 2),
    PRIMARY KEY (DishID, IngredientID),
    FOREIGN KEY (DishID) REFERENCES Dish(DishID),
    FOREIGN KEY (IngredientID) REFERENCES IngredientInventory(IngredientInventoryID)
);

select * from `Event`;

INSERT INTO Category (CategoryName, Age, Gender) VALUES
('Naruto', 12, 'Hombre'),
('SPYxFamily', 12, 'Mujeres'),
('Jujutsu Kaisen', 12, 'Hombre'),
('Harry Potter', 10, 'Hombre'),
('Comics', 15, 'Hombre');

INSERT INTO TriviaContest (Name, CategoryID) VALUES
('Concurso Naruto', 1),
('Concurso SPYxFamily', 2),
('Concurso Jujutsu Kaisen', 3),
('Concurso Harry Potter', 4),
('Concurso Comics', 5);

INSERT INTO TriviaQuestion (Question, CorrectAnswer, Category, Difficulty) VALUES
-- Preguntas de Naruto
('¿Quién es el sensei del Equipo 7?', 'Kakashi Hatake', 'Naruto', 'Facil'),
('¿Qué significa el nombre Naruto?', 'Remolino', 'Naruto', 'Intermedio'),
('¿Quién selló al Kyubi en Naruto?', 'Minato Namikaze', 'Naruto', 'Dificil'),
('¿Cuál es el verdadero nombre de Itachi Uchiha?', 'Itachi', 'Naruto', 'Facil'),
('¿Cuál es la técnica especial de Naruto?', 'Rasengan', 'Naruto', 'Intermedio'),
('¿Quién es el hermano de Sasuke?', 'Itachi Uchiha', 'Naruto', 'Facil'),
('¿Cuál es el poder ocular de Sasuke?', 'Sharingan', 'Naruto', 'Facil'),
('¿Quién es el mejor amigo de Naruto?', 'Sasuke Uchiha', 'Naruto', 'Facil'),
('¿Cómo se llama el sapo gigante que invoca Naruto?', 'Gamabunta', 'Naruto', 'Intermedio'),
('¿Quién es el actual Hokage en la serie?', 'Naruto Uzumaki', 'Naruto', 'Facil'),

-- Preguntas de SPYxFamily
('¿Cómo se llama la hija adoptiva de Loid Forger?', 'Anya Forger', 'SPYxFamily', 'Facil'),
('¿Qué poder especial tiene Anya Forger?', 'Telepatía', 'SPYxFamily', 'Intermedio'),
('¿Cuál es la ocupación de Yor Forger?', 'Asesina', 'SPYxFamily', 'Dificil'),
('¿Cómo se llama el perro de la familia Forger?', 'Bond', 'SPYxFamily', 'Facil'),
('¿Cuál es la misión de Loid Forger?', 'Salvar al mundo', 'SPYxFamily', 'Facil'),
('¿Qué alias tiene Yor Forger en su trabajo?', 'Thorn Princess', 'SPYxFamily', 'Intermedio'),
('¿Cuál es el nombre en clave de Loid Forger?', 'Twilight', 'SPYxFamily', 'Intermedio'),
('¿Dónde trabaja Yor Forger como cobertura?', 'Ayuntamiento', 'SPYxFamily', 'Facil'),
('¿En qué institución educativa estudia Anya?', 'Eden Academy', 'SPYxFamily', 'Intermedio'),
('¿Cuál es el sueño de Anya?', 'Ser una heroína', 'SPYxFamily', 'Facil'),

-- Preguntas de Jujutsu Kaisen
('¿Quién es el protagonista de Jujutsu Kaisen?', 'Yuji Itadori', 'Jujutsu Kaisen', 'Facil'),
('¿Cuál es el poder maldito de Satoru Gojo?', 'Limitless', 'Jujutsu Kaisen', 'Intermedio'),
('¿Cómo se llama el objeto maldito que Yuji ingiere?', 'Dedo de Sukuna', 'Jujutsu Kaisen', 'Facil'),
('¿Quién es el villano principal de la serie?', 'Sukuna', 'Jujutsu Kaisen', 'Facil'),
('¿Cuál es la escuela a la que asisten los personajes principales?', 'Escuela Técnica de Magia Metropolitana de Tokio', 'Jujutsu Kaisen', 'Intermedio'),
('¿Cómo se llama el sensei de Yuji Itadori?', 'Satoru Gojo', 'Jujutsu Kaisen', 'Facil'),
('¿Quién es el compañero de Yuji en su equipo?', 'Megumi Fushiguro', 'Jujutsu Kaisen', 'Facil'),
('¿Cuál es la especialidad de Nobara Kugisaki?', 'Control de clavos', 'Jujutsu Kaisen', 'Intermedio'),
('¿Qué le ocurre a Yuji después de ingerir el dedo de Sukuna?', 'Se convierte en su recipiente', 'Jujutsu Kaisen', 'Facil'),
('¿Qué es una Técnica Inversa en Jujutsu Kaisen?', 'Una técnica de curación', 'Jujutsu Kaisen', 'Dificil'),

-- Preguntas de Harry Potter
('¿Cómo se llama el colegio de magia en Harry Potter?', 'Hogwarts', 'Harry Potter', 'Facil'),
('¿Quién es el director de Hogwarts al inicio de la serie?', 'Albus Dumbledore', 'Harry Potter', 'Facil'),
('¿Qué animal es la mascota de Harry Potter?', 'Búho', 'Harry Potter', 'Intermedio'),
('¿Qué hechizo se utiliza para desarmar al oponente?', 'Expelliarmus', 'Harry Potter', 'Facil'),
('¿Quién es el guardián de las llaves en Hogwarts?', 'Rubeus Hagrid', 'Harry Potter', 'Intermedio'),
('¿Cuál es el nombre del elfo doméstico que ayuda a Harry?', 'Dobby', 'Harry Potter', 'Intermedio'),
('¿Cómo se llama la profesora de transformaciones?', 'Minerva McGonagall', 'Harry Potter', 'Intermedio'),
('¿Qué objeto debe atrapar un buscador en un partido de Quidditch?', 'Snitch dorada', 'Harry Potter', 'Facil'),
('¿Quién es el autor de la serie Harry Potter?', 'J.K. Rowling', 'Harry Potter', 'Facil'),
('¿Cómo se llama el villano principal de la serie?', 'Voldemort', 'Harry Potter', 'Facil'),

-- Preguntas de Comics
('¿Cómo se llama el alter ego de Batman?', 'Bruce Wayne', 'Comics', 'Facil'),
('¿Qué superhéroe es conocido como el Hombre de Acero?', 'Superman', 'Comics', 'Facil'),
('¿Cuál es el nombre real de Spider-Man?', 'Peter Parker', 'Comics', 'Facil'),
('¿Qué heroína es conocida como la Princesa Amazona?', 'Wonder Woman', 'Comics', 'Facil'),
('¿Quién es el archienemigo de Batman?', 'Joker', 'Comics', 'Facil'),
('¿Qué grupo de superhéroes está formado por Hulk, Thor, Iron Man y Capitán América?', 'Los Vengadores', 'Comics', 'Intermedio'),
('¿Quién es el rey de Wakanda en los cómics de Marvel?', 'T\'Challa', 'Comics', 'Intermedio'),
('¿Qué villano tiene la habilidad de controlar el metal?', 'Magneto', 'Comics', 'Intermedio'),
('¿Cuál es el nombre del martillo de Thor?', 'Mjolnir', 'Comics', 'Facil'),
('¿Quién es conocido como el Mercenario Bocazas?', 'Deadpool', 'Comics', 'Facil');

select * from `TriviaQuestion`;