-- Active: 1724865412630@@bm8yuvtf6fnhrm8sbkew-mysql.services.clever-cloud.com@3306
create database bm8yuvtf6fnhrm8sbkew;

use bm8yuvtf6fnhrm8sbkew;

CREATE TABLE Event (
    EventID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) UNIQUE NOT NULL,
    Country VARCHAR(50) NOT NULL,
    City VARCHAR(50) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    MaxPersonCapacity INT NOT NULL,
    MaxStoreCapacity INT NOT NULL,
    MaxRestaurantCapacity INT NOT NULL,
    Date DATE NOT NULL,
    Time TIME NOT NULL,
    Organizer VARCHAR(100),
    AgeRating VARCHAR(20),
    Status ENUM('Activo', 'Completado', 'Pendiente') NOT NULL
);

CREATE TABLE ActivityRole (
    ActivityRoleID INT AUTO_INCREMENT PRIMARY KEY,
    ActivityName VARCHAR(100) NOT NULL
);

CREATE TABLE Role (
    RoleID INT AUTO_INCREMENT PRIMARY KEY,
    RoleName VARCHAR(100) NOT NULL,
    Activity1ID INT,
    Activity2ID INT,
    FOREIGN KEY (Activity1ID) REFERENCES ActivityRole(ActivityRoleID),
    FOREIGN KEY (Activity2ID) REFERENCES ActivityRole(ActivityRoleID)
);

CREATE TABLE Staff (
    StaffID INT AUTO_INCREMENT PRIMARY KEY,
    EventID INT NOT NULL,
    Name VARCHAR(100) NOT NULL,
    Identification VARCHAR(50) UNIQUE NOT NULL,
    DateOfBirth DATE NOT NULL,
    RoleID INT NOT NULL,
    Status ENUM('Trabajo Asignado', 'No trabajando', 'Despedido', 'Incapacitado') NOT NULL,
    FOREIGN KEY (EventID) REFERENCES Event(EventID),
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

CREATE TABLE Props (
    PropID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Quantity INT NOT NULL,
    Status ENUM('En almacen', 'En sitio') NOT NULL,
    EventID INT NOT NULL,
    FOREIGN KEY (EventID) REFERENCES Event(EventID)
);

CREATE TABLE TicketOffice (
    TicketOfficeID INT AUTO_INCREMENT PRIMARY KEY,
    EventID INT NOT NULL,
    Location VARCHAR(100) NOT NULL,
    ContactNumber VARCHAR(15),
    StaffInChargeID INT,
    FOREIGN KEY (EventID) REFERENCES Event(EventID),
    FOREIGN KEY (StaffInChargeID) REFERENCES Staff(StaffID)
);

CREATE TABLE Visitor (
    VisitorID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    IdentificationDocument VARCHAR(50) UNIQUE NOT NULL,
    Gender ENUM('Hombre', 'Mujer', 'Otros') NOT NULL,
    DateOfBirth DATE NOT NULL,
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15),
    Status ENUM('Participa', 'No Participa', 'Ganador') NOT NULL
);

CREATE TABLE Ticket (
    TicketID INT AUTO_INCREMENT PRIMARY KEY,
    TicketName VARCHAR(100) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    AgeRating VARCHAR(20),
    AdditionalCost DECIMAL(10, 2),
    Status ENUM('Pagado', 'Reservado') NOT NULL,
    VisitorID INT,
    TicketOfficeID INT,
    FOREIGN KEY (VisitorID) REFERENCES Visitor(VisitorID),
    FOREIGN KEY (TicketOfficeID) REFERENCES TicketOffice(TicketOfficeID)
);

CREATE TABLE Category (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    CategoryName VARCHAR(100) NOT NULL,
    Age INT NOT NULL,
    Gender ENUM('Hombre', 'Mujeres', 'Otros') NOT NULL
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
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
    CONSTRAINT unique_activity_per_event UNIQUE (EventID, Name, StartTime)
);


CREATE TABLE ActivityParticipation (
    ParticipationID INT AUTO_INCREMENT PRIMARY KEY,
    VisitorID INT,
    ActivityID INT,
    FOREIGN KEY (VisitorID) REFERENCES Visitor(VisitorID),
    FOREIGN KEY (ActivityID) REFERENCES Activity(ActivityID)
);

CREATE TABLE EventAccounting (
    AccountingID INT AUTO_INCREMENT PRIMARY KEY,
    EventID INT NOT NULL,
    ActivityID INT NOT NULL,
    TicketsSold INT NOT NULL,
    ActivityParticipation INT NOT NULL,
    TotalAmount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (EventID) REFERENCES Event(EventID),
    FOREIGN KEY (ActivityID) REFERENCES Activity(ActivityID)
);

CREATE TABLE Business (
    BusinessID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Type ENUM('Tienda', 'Restaurante') NOT NULL,
    InChargeID INT,
    FOREIGN KEY (InChargeID) REFERENCES Staff(StaffID)
);

CREATE TABLE Prize (
    PrizeID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(50) NOT NULL,
    Description VARCHAR(255),
    Value DECIMAL(10, 2),
    Status ENUM('Disponible', 'Entregado') NOT NULL,
    ActivityID INT,
    VisitorID INT,
    BusinessID INT,
    FOREIGN KEY (ActivityID) REFERENCES Activity(ActivityID),
    FOREIGN KEY (VisitorID) REFERENCES Visitor(VisitorID),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID)
);

CREATE TABLE CosplayContest (
    CosplayContestID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    CategoryID INT NOT NULL,
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

CREATE TABLE CosplayParticipant (
    ParticipationID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Score DECIMAL(5, 2) NOT NULL,
    CosplayContestID INT NOT NULL,
    FOREIGN KEY (ParticipationID) REFERENCES ActivityParticipation(ParticipationID),
    FOREIGN KEY (CosplayContestID) REFERENCES CosplayContest(CosplayContestID)
);

CREATE TABLE TriviaContest (
    TriviaContestID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    CategoryID INT NOT NULL,
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

CREATE TABLE TriviaQuestion (
    QuestionID INT AUTO_INCREMENT PRIMARY KEY,
    Question VARCHAR(255) NOT NULL,
    CorrectAnswer VARCHAR(255) NOT NULL,
    Category VARCHAR(100) NOT NULL,
    Difficulty ENUM('Facil', 'Intermedio', 'Dificil') NOT NULL
);

CREATE TABLE TriviaParticipant (
    ParticipationID INT AUTO_INCREMENT PRIMARY KEY,
    Score DECIMAL(5, 2) NOT NULL,
    TriviaContestID INT NOT NULL,
    FOREIGN KEY (ParticipationID) REFERENCES ActivityParticipation(ParticipationID),
    FOREIGN KEY (TriviaContestID) REFERENCES TriviaContest(TriviaContestID)
);

CREATE TABLE QuestionParticipant (
    QuestionID INT NOT NULL,
    ParticipationID INT NOT NULL,
    PRIMARY KEY (QuestionID, ParticipationID),
    FOREIGN KEY (QuestionID) REFERENCES TriviaQuestion(QuestionID),
    FOREIGN KEY (ParticipationID) REFERENCES TriviaParticipant(ParticipationID)
);

CREATE TABLE BusinessStaff (
    StaffID INT NOT NULL,
    BusinessID INT NOT NULL,
    PRIMARY KEY (StaffID, BusinessID),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID)
);

CREATE TABLE CashRegister (
    CashRegisterID INT AUTO_INCREMENT PRIMARY KEY,
    Status ENUM('Activo', 'Inactivo') NOT NULL,
    OpeningAmount DECIMAL(10, 2) NOT NULL,
    ClosingAmount DECIMAL(10, 2),
    BusinessStaffID INT NOT NULL,
    FOREIGN KEY (BusinessStaffID) REFERENCES BusinessStaff(StaffID)
);

CREATE TABLE Orderr (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    VisitorID INT NOT NULL,
    BusinessID INT NOT NULL,
    CashRegisterID INT NOT NULL,
    TotalValue DECIMAL(10, 2) NOT NULL,
    Status ENUM('Registrado', 'Pagado', 'Entregado') NOT NULL,
    FOREIGN KEY (VisitorID) REFERENCES Visitor(VisitorID),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID),
    FOREIGN KEY (CashRegisterID) REFERENCES CashRegister(CashRegisterID)
);

CREATE TABLE OrderItem (
    OrderItemID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT NOT NULL,
    ItemName VARCHAR(100) NOT NULL,
    Quantity INT NOT NULL,
    IndividualValue DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orderr(OrderID)
);

CREATE TABLE StoreInventory (
    InventoryID INT AUTO_INCREMENT PRIMARY KEY,
    BusinessID INT NOT NULL,
    ProductName VARCHAR(100) NOT NULL,
    Description VARCHAR(255),
    Manufacturer VARCHAR(100),
    Type VARCHAR(50),
    AvailableQuantity INT NOT NULL,
    IndividualPrice DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID)
);

CREATE TABLE DiscountPromotion (
    DiscountID INT AUTO_INCREMENT PRIMARY KEY,
    InventoryID INT NOT NULL,
    Description VARCHAR(255),
    Type VARCHAR(50),
    DiscountValue DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (InventoryID) REFERENCES StoreInventory(InventoryID)
);

CREATE TABLE Dish (
    DishID INT AUTO_INCREMENT PRIMARY KEY,
    Description VARCHAR(255) NOT NULL,
    Type ENUM('Entrada', 'Bebida', 'Plato Fuerte', 'Aperitivo') NOT NULL,
    PreparationTimeMinutes INT NOT NULL
);

CREATE TABLE RestaurantMenu (
    BusinessID INT NOT NULL,
    DishID INT NOT NULL,
    AvailableQuantity INT NOT NULL,
    PRIMARY KEY (BusinessID, DishID),
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID),
    FOREIGN KEY (DishID) REFERENCES Dish(DishID)
);

CREATE TABLE Ingredient (
    IngredientID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Description VARCHAR(255),
    Supplier VARCHAR(100)
);

CREATE TABLE DishIngredient (
    DishID INT NOT NULL,
    IngredientID INT NOT NULL,
    RequiredQuantity DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (DishID, IngredientID),
    FOREIGN KEY (DishID) REFERENCES Dish(DishID),
    FOREIGN KEY (IngredientID) REFERENCES Ingredient(IngredientID)
);

CREATE TABLE IngredientInventory (
    IngredientInventoryID INT AUTO_INCREMENT PRIMARY KEY,
    BusinessID INT NOT NULL,
    IngredientID INT NOT NULL,
    AvailableQuantity DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (BusinessID) REFERENCES Business(BusinessID),
    FOREIGN KEY (IngredientID) REFERENCES Ingredient(IngredientID)
);



select * from TriviaQuestion;

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