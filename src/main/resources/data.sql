-- Inserir dados na tabela user_parent
INSERT INTO public.user_parent (cep, phone, email, "name", "password", tb_user)
VALUES
    (12345678, 98765432, 'user1@example.com', 'User 1', 'password1', 'customer1'),
    (23456789, 87654321, 'user2@example.com', 'User 2', 'password2', 'employee1'),
    (34567890, 76543210, 'user3@example.com', 'User 3', 'password3', 'hotel1'),
    (45678901, 65432109, 'user4@example.com', 'User 4', 'password4', 'customer2'),
    (56789012, 54321098, 'user5@example.com', 'User 5', 'password5', 'employee2'),
    (67890123, 43210987, 'user6@example.com', 'User 6', 'password6', 'hotel2'),
    (78901234, 32109876, 'user7@example.com', 'User 7', 'password7', 'customer3'),
    (89012345, 21098765, 'user8@example.com', 'User 8', 'password8', 'employee3'),
    (90123456, 10987654, 'user9@example.com', 'User 9', 'password9', 'hotel3');

-- Inserir dados na tabela customer
INSERT INTO public.customer (id, cpf)
VALUES
    (1, '12345678901'),
    (4, '23456789012'),
    (7, '34567890123');



-- Inserir dados na tabela hotel
INSERT INTO public.hotel (id, cnpj, descr)
VALUES
    (3, '12345678901234', 'Hotel 1'),
    (6, '23456789012345', 'Hotel 2'),
    (9, '34567890123456', 'Hotel 3');
    
-- Inserir dados na tabela employee
INSERT INTO public.employee (hotel_id, id, cpf)
VALUES
    (3, 2, '98765432109'),
    (6, 5, '87654321098'),
    (9, 8, '76543210987');
    
-- Inserir dados na tabela room_type
INSERT INTO public.room_type (daily, id, qt, descr)
VALUES
    (100.0, 1, 1, 'Single Room'),
    (150.0, 2, 2, 'Double Room'),
    (200.0, 3, 4, 'Suite');
    
-- Inserir dados na tabela hotel_room
INSERT INTO public.hotel_room (hotel_id, id, num, type_id)
VALUES
    (3, 1, 101, 1),
    (3, 2, 102, 1),
    (6, 3, 201, 2);

-- Inserir dados na tabela reservation
INSERT INTO public.reservation (customer_id, employee_id, room_id, fin, init)
VALUES
    (1, null, 1, '2023-06-28 00:00:00', '2023-06-29 00:00:00'),
    (4, null, 2, '2023-07-01 00:00:00', '2023-07-03 00:00:00'),
    (7, null, 3, '2023-07-05 00:00:00', '2023-07-07 00:00:00');


