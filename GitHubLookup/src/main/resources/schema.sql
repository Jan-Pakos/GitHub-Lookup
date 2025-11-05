create table if not exists repo (
                                    id BIGSERIAL PRIMARY KEY,
                                    owner VARCHAR NOT NULL,
                                    name VARCHAR NOT NULL
);