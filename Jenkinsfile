pipeline {
    agent any
    tools {
        maven 'mvn-3.6.3'
    }
    stages {
        stage('Build') {
            steps{
                bat "mvn clean package spring-boot:repackage"
                bat "set"
            }
        }
    }
}