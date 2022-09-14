# Install LTS version of Java
FROM adoptopenjdk:latest

# Create directory for app
RUN mkdir /app

# Set as current directory for RUN, ADD, COPY commands
WORKDIR /app

# Add Gradle from upstream
COPY gradle ./gradle
COPY gradlew ./
COPY gradlew.bat ./
COPY build.gradle ./
COPY settings.gradle ./
COPY gradle ./

# Install dependencies
RUN { \
    echo; \
    echo 'task fetchDependencies { description "Pre-downloads *most* dependencies"'; \
    echo 'doLast { configurations.getAsMap().each { name, config ->'; \
    echo 'print "Fetching dependencies for $name..."'; \
    echo 'try { config.files; println "done" }'; \
    echo 'catch (e) { println ""; project.logger.info e.message; }'; \
    echo '} } }'; \
    echo; \
} >>/app/build.gradle

RUN ./gradlew --no-daemon clean fetchDependencies

# Add entire student fork (overwrites previously added files)
ARG SUBMISSION_SUBFOLDER
COPY $SUBMISSION_SUBFOLDER ./

# Overwrite files in student fork with upstream files
COPY assessment ./assessment
COPY test.sh build.gradle ./
