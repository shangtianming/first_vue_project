// Declarative类型 
pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                // 这里是Groovy语法，写了一个打印语句
                println "Build" 
            }
        }
        stage('Test') { 
            steps {
                // 在linux上执行
                sh "echo `pwd`"
            }
        }
        stage('Deploy') { 
            steps {
                println "Deploy" 
            }
        }
    }
}

// Scripted模式
// node {  
//     stage('Build') { 
//         // 
//     }
//     stage('Test') { 
//         // 
//     }
//     stage('Deploy') { 
//         // 
//     }
// }