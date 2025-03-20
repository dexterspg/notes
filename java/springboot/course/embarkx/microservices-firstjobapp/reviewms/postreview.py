#NOT TESTED 
#NOT TESTED 
#NOT TESTED 
#NOT TESTED 
#NOT TESTED 
#NOT TESTED 
import requests

job_id=None
company_id=None
review_id=None

class HttpMethod:
    GET = "GET"
    POST = "POST"
    PUT = "PUT"
    DELETE = "DELETE"


METHOD = {
    "1": HttpMethod.GET,
    "2": HttpMethod.POST,
    "3": HttpMethod.PUT,
    "4": HttpMethod.DELETE
}

def select_endpoint():
    print("""
    1 - Jobs
    2 - Companies
    3 - Reviews
    """)
    endpoint_no = input("Select number: ").strip()

    if endpoint_no == "1":
        return "jobs"
    elif endpoint_no == "2":
        return "companies"
    elif endpoint_no == "3":
        return "reviews"
    else:
        print("Invalid selection. Please select a valid endpoint.")
        exit()


def select_method():
    print("""
    1 - GET
    2 - POST
    3 - PUT
    4 - DELETE
    """)
    method_type = input("Select number: ").strip()

    if method_type in METHOD:
        return METHOD[method_type]
    else:
        print("Invalid HTTP method selected.")
        exit()


def get_request_data(endpoint):
    # Custom data input depending on the endpoint
    if endpoint == "reviews":
        body = {
            "title": f"Review Title {review_id}",
            "description": f"Review description goes here. {review_id}",
            "rating": f"{review_id}",
        }
    else:
        body = {
            "name": f"Software Innovators {company_id}",
            "description": f"A leading company in AI and cloud solutions. {company_id}",
        }
    return body


def send_request(method, url, headers, body=None):
    try:
        if method == HttpMethod.GET:  # GET
            response = requests.get(url)
        elif method == HttpMethod.POST:
            response = requests.post(url, headers=headers, json=body)
        elif method == HttpMethod.PUT:
            response = requests.put(url, headers=headers, json=body)
        elif method == HttpMethod.DELETE:
            response = requests.delete(url)
        else:
            print("Invalid HTTP method.")
            exit()

        # Handle response
        print(f"Status Code: {response.status_code}")
        print(f"Response Body: {response.text}")

    except requests.exceptions.RequestException as e:
        print(f"An error occurred: {e}")


# Main Program
if __name__ == "__main__":
    endpoint = select_endpoint()
    method = select_method()

    headers = {"Content-Type": "application/json"}
    # Construct the URL
    if endpoint == "reviews":
        company_id = input("Enter company ID: ").strip()
        if method == HttpMethod.PUT or method == HttpMethod.DELETE:
            review_id = input("Enter review ID: ").strip()
            url = f"http://localhost:8080/companies/{company_id}/reviews/{review_id}"
        else:
            url = f"http://localhost:8080/companies/{company_id}/reviews"
    else:
        url = f"http://localhost:8080/{endpoint}"

    # Only include a body for POST and PUT requests
    body=None
    if method == HttpMethod.POST or method == HttpMethod.PUT:  # POST or PUT
        body = get_request_data(endpoint)

    # Send the request
    send_request(method, url, headers, body)

