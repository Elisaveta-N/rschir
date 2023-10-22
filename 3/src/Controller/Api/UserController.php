<?php
class UserController extends BaseController
{
    /*
    public function listAction()
    {
        $strErrorDesc = '';
        $requestMethod = $_SERVER["REQUEST_METHOD"];
        $arrQueryStringParams = $this->getQueryStringParams();
        if (strtoupper($requestMethod) == 'GET') {
            try {
                $userModel = new UserModel();
                $intLimit = 10;
                if (isset($arrQueryStringParams['limit']) && $arrQueryStringParams['limit']) {
                    $intLimit = $arrQueryStringParams['limit'];
                }
                $arrUsers = $userModel->getUsers($intLimit);
                $responseData = json_encode($arrUsers);
            } catch (Error $e) {
                $strErrorDesc = $e->getMessage().'Something went wrong! Please contact support.';
                $strErrorHeader = 'HTTP/1.1 500 Internal Server Error';
            }
        } else {
            $strErrorDesc = 'Method not supported';
            $strErrorHeader = 'HTTP/1.1 422 Unprocessable Entity';
        }
        // send output 
        if (!$strErrorDesc) {
            $this->sendOutput(
                $responseData,
                array('Content-Type: application/json', 'HTTP/1.1 200 OK')
            );
        } else {
            $this->sendOutput(json_encode(array('error' => $strErrorDesc)), 
                array('Content-Type: application/json', $strErrorHeader)
            );
        }
    }
*/	

	public function getUsers()
    {
		try {
			$userModel = new UserModel();
			$users = $userModel->getUsers();
			if ($users == NULL) {
				http_response_code(404); //Not found
			}
			else {
				http_response_code(200);				     
			}
			echo json_encode($users);				
		} 
		catch (Error $e) {
			http_response_code(500);
			echo json_encode('Something went wrong');
		}
    }

	public function getUser($id)
    {
		try {
			$userModel = new UserModel();
			$user = $userModel->getUser($id);
			if ($user == NULL) {
				http_response_code(404); //Not found
			}
			else {
				http_response_code(200);				     
			}
			echo json_encode($user);				
		} 
		catch (Error $e) {
			http_response_code(500);
			echo json_encode('Something went wrong');
		}
    }

	public function createUser($data)
    {
		try {
			$username = '';
			$user_email = '';
			$user_status = '';
						
			if(array_key_exists("username",$data) && array_key_exists("user_email",$data) && array_key_exists("user_status",$data)) {			
				$username = $data['username'];
				$user_email = $data['user_email'];
				$user_status = $data['user_status'];
			}
			
			if(!empty($username) && !empty($user_email) && !empty($user_status)) {			
				$userModel = new UserModel();
				$userModel->addUser($username, $user_email, $user_status);
				http_response_code(200);
				echo json_encode('User created OK');	
			}		
			else {
				http_response_code(400);
				echo json_encode('Bad params');
			}				
		} 
		catch (Error $e) {
			http_response_code(500);
			echo json_encode('Something went wrong');
		}
    }	
	
	public function updateUser($data, $id)
    {
		try {
			$username = '';
			$user_email = '';
			$user_status = '';
			$user_id = $id;
						
			if(array_key_exists("username",$data) && array_key_exists("user_email",$data) && array_key_exists("user_status",$data)) {			
				$username = $data['username'];
				$user_email = $data['user_email'];
				$user_status = $data['user_status'];
			}
			
			if(!empty($username) && !empty($user_email) && !empty($user_status) && !empty($user_id)) {			
				$userModel = new UserModel();
				$userModel->updateUser($user_id, $username, $user_email, $user_status);
				http_response_code(200);
				echo json_encode('User updated OK');	
			}		
			else {
				http_response_code(400);
				echo json_encode('Bad params');
			}				
		} 
		catch (Error $e) {
			http_response_code(500);
			echo json_encode('Something went wrong');
		}
    }	

	public function deleteUser($id)
    {
		try {
			$userModel = new UserModel();
			$userModel->deleteUser($id);
			http_response_code(200);
			echo json_encode('User delete OK');				
		} 
		catch (Error $e) {
			http_response_code(500);
			echo json_encode("Something went wrong");
		}
    }	
	
}