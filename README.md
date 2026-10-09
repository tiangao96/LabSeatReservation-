# LabWorkstation

A web-based **laboratory workstation reservation and usage-accounting platform** for
university teaching laboratories.

> Visualizing seat allocation and automating space-charge calculations in shared
> university laboratories: *The LabWorkstation platform*.

## Features

- Laboratory rooms and **workstation layout modelling** (seat / door / window primitives)
- Reservation request submission, review and approval workflow
- Monthly rental (usage) accounting with room-area and seat statistics
- Occupancy history with **two-stage conflict detection**
- Workstation status state machine (`IDLE / OCCUPIED / PRE_RESERVED / MAINTENANCE / DISABLED`)
- Role-based access control (RBAC) with menus, roles and permissions
- Operation audit log; automatic release of workstations on student graduation
- Chinese / English runtime switching

## Repository layout

```
src/                 Backend source (Spring Boot 3.5, Java 17, Maven)
pom.xml mvnw mvnw.cmd Maven build (wrapper included)
frontend/            Built frontend assets (Vue 3 + Vben Admin), served as static files
sql/                 schema.sql (DDL), data.sql (demo seed), mentor_menu.sql
deploy/              nginx config, dev/deploy scripts, app.env.example
Dockerfile docker-compose.yml
LICENSE CITATION.cff
```

## Build & run (backend)

```bash
./mvnw -DskipTests package         # produces target/lab-server-0.1.0.jar
# configure DB/Redis via environment (see deploy/app.env.example), then:
java -jar target/lab-server-0.1.0.jar --spring.profiles.active=prod
```

- Requires **JDK 17**, **MySQL 8 / MariaDB 10.5+** and **Redis**.
- Create the database with `sql/schema.sql`, then load `sql/data.sql` (demo accounts:
  `admin / admin123`, `user / user123`) and `sql/mentor_menu.sql`.
- The `frontend/` folder can be served directly by nginx (see `deploy/lab-reservation.conf`).

## Demo data

`sql/data.sql` seeds a **minimal demo** dataset (RBAC roles/menus, 1 lab, a couple of
workstations, demo accounts). It contains **no real personal data**.

## License

MIT License — see [LICENSE](LICENSE).

## Citation

See [CITATION.cff](CITATION.cff). A DOI will be provided upon archival (Zenodo).
