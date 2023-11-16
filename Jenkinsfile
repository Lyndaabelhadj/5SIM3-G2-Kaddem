pipeline {
   agent any

 stages {

       stage('Hello Houssem') {
           steps {
               echo 'Hello World'
           }
       }
       stage('Clean') {
           steps {
               echo 'Cleaning...'
               sh "mvn clean"
           }
       }
       stage('Build') {
           steps {
               echo 'Validating...'
               sh "mvn validate"
               echo 'Compiling...'
               sh "mvn compile"
           }
       }
       stage('testin maven') {
           steps {
                sh 'mvn -B -DskipTests clean package'
               echo 'mvn -version'
           }
       }
               stage('Docker Build image') {
                   steps {
                       sh 'docker build -t khaddem.jar .'
                   }
               }
       stage('Image deploy') {
                                 steps {
                                      sh 'docker login -u houssem9017 -p F@!yz~x==GJ-:3?'
                                      sh 'docker tag khaddem.jar houssem9017/khaddem:1.0'
                                      sh 'docker push houssem9017/khaddem:1.0'
                                 }
                             }
       stage('Docker compose') {
                                 steps {
                                      sh 'docker-compose -f <docker-compose.yml> logs'
                                 }
                             }

   }
}

