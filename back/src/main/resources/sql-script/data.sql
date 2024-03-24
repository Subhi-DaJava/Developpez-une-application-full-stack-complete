use mdd;

INSERT INTO `mdd`.`users` (`id`, `email`, `password`, `username`)
VALUES
    (1, 'userA@test.com', '$10$ZlfdnpgqTqe7Y.42e2thBur22WI35piRsCpQOnQF018WEKuNAGJCi', 'UserA'),
    (2, 'userB@test.com', '$10$ZlfdnpgqTqe7Y.42e2thBur22WI35piRsCpQOnQF018WEKuNAGJCi', 'UserB');

INSERT INTO `mdd`.`topics` (`topic_id`, `description`, `name`)
VALUES ('1', 'Java 17, released in September 2021, is the latest long-term support (LTS) version of the Java programming language.', 'Java 17'),
       ('2', 'Java 21 is the latest version of the Java programming language and development platform.', 'Java 21'),
       ('3', 'Spring Boot 3 is the latest iteration of the Spring Boot framework, designed to simplify the bootstrapping and development of Spring applications.', 'Spring Boot 3');

INSERT INTO `mdd`.`users_topics` (`user_id`, `topic_id`)
VALUES
    (1, 1),
    (2, 1),
    (1, 2),
    (1, 3);

INSERT INTO `mdd`.`posts` (`post_id`, `topic_id`, `user_id`, `title`, `created_at`, `content`)
VALUES
    (1, 1, 1, 'What’s new about Java 17?', '2024-01-28', 'Java 17, also known as Java SE 17, is a recent version of Java, offering enhanced performance, stability, and security for Java applications.'),
    (2, 3, 2, 'Spring Boot 3', '2024-01-29', 'Spring Boot 3 est la dernière version du framework Spring Boot, conçu pour simplifier le démarrage et le développement des applications Spring.'),
    (3, 3, 1, 'JavaScript and Angular: Modern Web Development', '2024-01-30', 'Explore the synergy between JavaScript and Angular for building modern web applications.'),
    (4, 3, 1, 'JavaScript and Angular with Jest', '2024-01-31', 'Explore the synergy between JavaScript and Angular while leveraging Jest for testing in modern web development.');

INSERT INTO `mdd`.`comments` (`comment_id`, `user_id`, `content`, `post_id`, `created_at`)
VALUES
    (1, 1, 'Cool! I like your article.', 1, '2024-01-28'),
    (2, 1, 'Je te remercie pour ton article, super!', 1, '2024-02-28'),
    (3, 2, 'How to update Spring Boot 3', 2, '2024-03-05');

-- N.B. --> mot de passe : Test!1234