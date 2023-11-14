pipeline {
   agent any

 stages {

       stage('Hello Rayen') {
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

   }
}
