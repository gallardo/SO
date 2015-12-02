# StackOverflow code snippets
(for supporting questions in SO)

Tests for http://stackoverflow.com/q/32561665/413020: change headers from inside an included servlet using the original response

##Instructions

Compile code:

    mvn clean package

Test code for each container:

    mvn {tomcat|tomcat7|jetty|wildfly}:run
    # In a different terminal
    mvn test -Dtest=TopServletIT
    # In the terminal where the container runs
    CTRL+C
