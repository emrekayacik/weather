INSERT INTO public.user_def(
    id, created_date, id_created_user, modified_date, id_modified_user, email, name, password, phone_number, role, surname, username)
VALUES (1001, null, null, null, null, 'emrekayacik2634@gmail.com', 'emre', '3673emre', '+905533095569', 'USER', 'kayacik', 'emrekayacik');

INSERT INTO public.user_def(
    id, created_date, id_created_user, modified_date, id_modified_user, email, name, password, phone_number, role, surname, username)
VALUES (1002, null, null, null, null, 'alpaygds@gmail.com', 'alpay', '123456789a', '+905551093519', 'USER', 'güder', 'alpaygds');

INSERT INTO public.user_def(
    id, created_date, id_created_user, modified_date, id_modified_user, email, name, password, phone_number, role, surname, username)
VALUES (1003, null, null, null, null, 'meteknl@gmail.com', 'mete', '1597530abc', '+905514752195', 'USER', 'kanli', 'meteknl');


INSERT INTO public.weather_saved(
    id, created_date, id_created_user, modified_date, id_modified_user, city_name, user_id)
VALUES (1001, null, null, null, null, 'paris', 1001);

INSERT INTO public.weather_saved(
    id, created_date, id_created_user, modified_date, id_modified_user, city_name, user_id)
VALUES (1002, null, null, null, null, 'astana', 1001);

INSERT INTO public.weather_saved(
    id, created_date, id_created_user, modified_date, id_modified_user, city_name, user_id)
VALUES (1003, null, null, null, null, 'sofia', 1002);

INSERT INTO public.weather_saved(
    id, created_date, id_created_user, modified_date, id_modified_user, city_name, user_id)
VALUES (1004, null, null, null, null, 'paris', 1002);

INSERT INTO public.weather_saved(
    id, created_date, id_created_user, modified_date, id_modified_user, city_name, user_id)
VALUES (1005, null, null, null, null, 'paris', 1003);

