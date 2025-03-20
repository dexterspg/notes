"""#!/bin/bash

LOG_LEVEL="INFO"

curl -X POST http://localhost:8080/actuator/loggers/com.embarx.firstjobapp.FirstjobappApplication \
-H "Content-Type: application/json" \
-d "{\"configuredLevel\": \"$LOG_LEVEL\"}" \
-w '\nHTTP Status: %{http_code}'
"""


import requests

# Define the log level
LOG_LEVEL = input("Enter log level (DEBUG, INFO, WARN, ERROR): ").strip()

LOG_LEVEL = "INFO" if LOG_LEVEL == "" else LOG_LEVEL


# Define the URL and headers
url = "http://localhost:8080/actuator/loggers/com.embarx.firstjobapp.FirstjobappApplication"
headers = {
    "Content-Type": "application/json"
}

# Define the JSON payload
data = {
    "configuredLevel": LOG_LEVEL
}

# Make the POST request
try:
    response = requests.post(url, headers=headers, json=data)
    
    # Print the response body
    print(response.text)
    
    # Print the HTTP status code
    print(f"HTTP Status: {response.status_code}")

except requests.exceptions.RequestException as e:
    print(f"An error occurred: {e}")
