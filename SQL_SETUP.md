# SmartBallot Database Setup

Run these commands in MySQL:

```sql
CREATE DATABASE smartballot CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- optional sample users
INSERT INTO users (student_id, name, has_voted) VALUES
('S123', 'Alice', false),
('S124', 'Bob', false);
