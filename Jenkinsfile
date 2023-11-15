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
       stage('Quality test SONARQUBE') {
           steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
               echo 'mvn -Sonarqube Analysis'
           }
       }
       stage('Deploy artifact with nexus') {
                   steps {
                        sh 'mvn deploy -DskipTests'
                   }
               }
               stage('Docker Build image') {
                   steps {
                       sh "mvn package -DskipTests"
                       sh 'docker build -t houssem9017/khaddem:1.0.1 .'
                   }
               }

               stage('Docker PUSH image') {
                   steps {
                       sh 'docker login -u houssem9017 -p F@!yz~x==GJ-:3?'
                       sh 'docker push houssem9017/khaddem:1.0.1'
                   }
               }
               stage("Removing testing container") {
                   steps {
                        sh "docker stop testing_container"
                        sh "docker rm testing_container"
                   }
               }

   }
}

