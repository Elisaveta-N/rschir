<?php

/*
require __DIR__ . "/inc/bootstrap.php";
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
$uri = explode( '/', $uri );
if ((isset($uri[2]) && $uri[2] != 'user') || !isset($uri[3])) {
    header("HTTP/1.1 404 Not Found");
    exit();
}
require PROJECT_ROOT_PATH . "/Controller/Api/UserController.php";
$objFeedController = new UserController();
$strMethodName = $uri[3] . 'Action';
$objFeedController->{$strMethodName}();
*/
	require __DIR__ . "/inc/bootstrap.php";
	$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
	$uri = explode( '/', $uri );
	
	if (!(isset($uri[2])) || $uri[2] != 'user') {
		http_response_code(400);
		echo json_encode('Bad request');
		exit();
	}
	
	require PROJECT_ROOT_PATH . "/Controller/Api/UserController.php";
	$objFeedController = new UserController();

	$method = strtoupper($_SERVER["REQUEST_METHOD"]);
	$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
	$uri = explode( '/', $uri );
	
	switch ($method) {
    case 'GET':
	
		$len = count($uri);
		if ($len == 3) {			
			$objFeedController->getUsers();
		}		
		elseif ($len == 4) {
			$objFeedController->getUser($uri[3]);
		}
		else {
			 http_response_code(400);
			 echo json_encode('Bad request');
		}		
        break;
		
    case 'POST':
		$data = json_decode(file_get_contents('php://input'), true);	
        $objFeedController->createUser($data);
        break;
		
    case 'PUT':
		$len = count($uri);
		if($len != 4) {
			 http_response_code(400);
			 echo json_encode('Bad request');			
		}
		else {		
			$data = json_decode(file_get_contents('php://input'), true);	
			$objFeedController->updateUser($data, $uri[3]);
		}
        break;	

		
    case 'DELETE':
		$len = count($uri);
		if($len != 4) {
			 http_response_code(400);
			 echo json_encode('Bad request');			
		}
		else {
			$objFeedController->deleteUser($uri[3]);
		}
        break;			
		
    default:
        http_response_code(400);
		echo json_encode('Bad request');	
        break;
}
	


?>