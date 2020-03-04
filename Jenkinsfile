pipeline {
    agent any
    tools {
        maven 'mvn-3.6.3'
    }
    stages {
        stage('Build') {
            steps{
                sh "mvn clean package spring-boot:repackage"
                sh "printenv"
            }
        }
    }
}