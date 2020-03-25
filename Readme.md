## Demo Application for Kzadha

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. PostgreSQL 9.4+

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/nurlanmoldabekov/kzadha-project.git
```

**2. Build and run the app using maven**

```bash
cd kzadha-project
mvn  clean package
java -jar target/kzadha-project.jar -DJDBC_DATABASE_URL=jdbc:postgresql://... -DJDBC_DATABASE_USERNAME=... -DJDBC_DATABASE_PASSWORD=...
```

Alternatively, you can run the app directly without packaging it like so -

```bash
mvn spring-boot:run -DJDBC_DATABASE_URL=jdbc:postgresql://... -DJDBC_DATABASE_USERNAME=... -DJDBC_DATABASE_PASSWORD=...
```

## TODO
1. Testings

2. Email validation and etc

3. DB Indexes