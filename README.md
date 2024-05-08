for reminder
docker exec -it mongodb_note mongosh "mongodb://admin_note:1234@localhost:27027/

don't forget to create db and account

use db_patients

db.createUser({
  user: "admin",
  pwd: "1234",
  roles: [
    { role: "dbAdmin", db: "db_patients" },
    { role: "readWrite", db: "db_patients" }
  ]
})
