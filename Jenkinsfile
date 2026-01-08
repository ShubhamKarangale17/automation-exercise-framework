pipeline {
    agent any

    environment {
        // Environment variables
        JAVA_HOME = "${tool 'JDK_11'}"
        MAVEN_HOME = "${tool 'Maven_3.8.1'}"
        PATH = "${MAVEN_HOME}/bin:${JAVA_HOME}/bin:${PATH}"
        PROJECT_NAME = "automation-exercise-framework"
        REPO_URL = "https://github.com/your-username/automation-exercise-framework.git"
        BRANCH = "main"
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 30, unit: 'MINUTES')
        timestamps()
    }

    parameters {
        choice(name: 'TEST_SUITE', choices: ['All Tests', 'UI Tests Only', 'API Tests Only'], description: 'Select tests to run')
        booleanParam(name: 'HEADLESS_MODE', defaultValue: true, description: 'Run browser in headless mode')
    }

    stages {
        stage('Checkout') {
            steps {
                echo "=== Checking out code from GitHub ==="
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "*/${BRANCH}"]],
                    userRemoteConfigs: [[url: "${REPO_URL}"]]
                ])
                echo "✓ Code checked out successfully"
            }
        }

        stage('Build') {
            steps {
                echo "=== Building project with Maven ==="
                sh 'mvn clean compile'
                echo "✓ Project built successfully"
            }
        }

        stage('Prepare Test Environment') {
            steps {
                echo "=== Preparing test environment ==="
                sh 'mkdir -p reports/screenshots'
                sh 'echo "Test environment prepared"'
                echo "✓ Test environment ready"
            }
        }

        stage('Run Tests') {
            parallel {
                stage('API Tests') {
                    when {
                        expression { params.TEST_SUITE == 'All Tests' || params.TEST_SUITE == 'API Tests Only' }
                    }
                    steps {
                        echo "=== Running API Tests ==="
                        sh '''
                            mvn test -Dgroups=api \
                                -Dtest=api.* \
                                -DbaseUrl=https://fakestoreapi.com
                        '''
                        echo "✓ API tests completed"
                    }
                }

                stage('UI Tests') {
                    when {
                        expression { params.TEST_SUITE == 'All Tests' || params.TEST_SUITE == 'UI Tests Only' }
                    }
                    steps {
                        echo "=== Running UI Tests ==="
                        sh '''
                            mvn test -Dgroups=ui \
                                -Dtest=ui.* \
                                -DbaseUrl=https://automationexercise.com \
                                -Dheadless=${HEADLESS_MODE}
                        '''
                        echo "✓ UI tests completed"
                    }
                }
            }
        }

        stage('Generate Reports') {
            steps {
                echo "=== Generating test reports ==="
                sh 'echo "Reports generated in reports/ directory"'
                archiveArtifacts artifacts: 'reports/**/*.html, target/surefire-reports/**', allowEmptyArchive: true
                echo "✓ Reports archived"
            }
        }

        stage('Publish Results') {
            steps {
                echo "=== Publishing test results ==="
                junit skipPublishingChecks: true, testResults: 'target/surefire-reports/**/*.xml'
                echo "✓ Test results published"
            }
        }
    }

    post {
        always {
            echo "=== Cleaning up ==="
            cleanWs(deleteDirs: true, patterns: [[pattern: 'node_modules/**', type: 'INCLUDE']])
        }

        success {
            echo "✓ Pipeline executed successfully!"
            // Add notification (e.g., email, Slack)
        }

        failure {
            echo "✗ Pipeline failed! Check logs for details"
            // Add notification (e.g., email, Slack)
        }

        unstable {
            echo "⚠ Pipeline unstable"
        }
    }
}
