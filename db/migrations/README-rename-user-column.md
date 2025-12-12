Postgres migration: rename reserved column `user` to `user_id`

If your database was created with a column named `user` in `sales` or `address`, Postgres may produce SQL errors when inserting because `user` is a reserved word.

Run the following SQL statements on your Postgres database (adjust schema if needed):

```sql
ALTER TABLE sales RENAME COLUMN "user" TO user_id;
ALTER TABLE address RENAME COLUMN "user" TO user_id;
```

After running the above, restart the backend and try creating a sale again.

Note: If your DB has foreign keys or constraints that reference these columns, Postgres will update them automatically when renaming the column. If you prefer, create a backup before running the migration.
