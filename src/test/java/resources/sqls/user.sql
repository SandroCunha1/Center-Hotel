delete from user_parent;

-- Inserir dados na tabela user_parent
INSERT INTO public.user_parent (id, cep, phone, email, name, password, tb_user)
VALUES
    (20, 12345678, 98765432, 'user1@example.com', 'User 1', 'password1', 'customer1'),
    (30, 45678901, 65432109, 'user4@example.com', 'User 2', 'password4', 'customer2'),
    (40, 78901234, 32109876, 'user7@example.com', 'User 3', 'password7', 'customer3'),
    (50, 12345678, 98765432, 'user1as@example.com', 'User 4', 'password232', 'customer2'),
    (60, 45678901, 65432109, 'user4asd@example.com', 'User 5', 'password423', 'customer3'),
    (70, 78901234, 32109876, 'user7asd@example.com', 'User 6', 'password723', 'customer4');