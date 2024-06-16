CREATE TABLE likes
(
   id BIGINT PRIMARY KEY,
   timestamp TIMESTAMP NOT NULL,
   fromBrand_id BIGINT,
   toBrand_id BIGINT,
   FOREIGN KEY (fromBrand_id) REFERENCES brands(id),
   FOREIGN KEY (toBrand_id) REFERENCES brands(id)
);

COMMENT ON TABLE likes IS 'Таблица хранения информации о лайках';
COMMENT ON COLUMN likes.id IS 'Уникальный идентификатор лайка';
COMMENT ON COLUMN likes.fromBrand_id IS 'От кого лайк';
COMMENT ON COLUMN likes.toBrand_id IS 'Кому лайк';
