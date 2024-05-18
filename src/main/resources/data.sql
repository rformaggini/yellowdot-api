INSERT INTO tb_roles (role_id, name) VALUES (1, 'ADMIN') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_roles (role_id, name) VALUES (2, 'BASIC') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_roles (role_id, name) VALUES (3, 'STAFF') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_roles (role_id, name) VALUES (4, 'COSTUMER') ON CONFLICT (role_id) DO NOTHING;

INSERT INTO tb_categories (category_id, name) VALUES (1, 'Pint') ON CONFLICT (category_id) DO NOTHING;
INSERT INTO tb_categories (category_id, name) VALUES (2, 'Fizzy Drinks') ON CONFLICT (category_id) DO NOTHING;
INSERT INTO tb_categories (category_id, name) VALUES (3, 'Pizza') ON CONFLICT (category_id) DO NOTHING;

INSERT INTO tb_products (product_id, created_at, description, name, price, status, category_id)
    VALUES (1, current_timestamp, 'USE FOR WEEKENDS', 'PIZZA MARGUERITA', 8, 'ACTIVE', 3) ON CONFLICT (product_id) DO NOTHING;
INSERT INTO tb_products (product_id, created_at, description, name, price, status, category_id)
    VALUES (2, current_timestamp, 'USE FOR DINNER', 'SODA', 3, 'ACTIVE', 2) ON CONFLICT (product_id) DO NOTHING;
