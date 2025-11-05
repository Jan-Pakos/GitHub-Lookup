CREATE TABLE  IF NOT EXISTS repo
(
    id    BIGSERIAL PRIMARY KEY,
    owner VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL
);

DROP TABLE repo;

ALTER TABLE repo
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;

\d repo;
-- or
SELECT column_name, column_default, is_nullable, data_type
FROM information_schema.columns
WHERE table_name = 'repo' AND column_name = 'id';

ALTER TABLE repo
    ALTER COLUMN id DROP DEFAULT;

ALTER TABLE repo
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;

DROP SEQUENCE repo_id_seq;

-- Insert a sample repository for owner 'juniordev'
INSERT INTO repo (owner, name)
VALUES ('juniordev', 'spring-data-project');

-- Insert a repository for a well-known user
INSERT INTO repo (owner, name)
VALUES ('spring-projects', 'spring-boot-quickstart');

-- Insert a second repository for the first user to test retrieval by owner
INSERT INTO repo (owner, name)
VALUES ('juniordev', 'java-feign-client');

-- Insert a repository with a longer name
INSERT INTO repo (owner, name)
VALUES ('java_enthusiast', 'advanced-design-patterns-in-java');

select * from repo;