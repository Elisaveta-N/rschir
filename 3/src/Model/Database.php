<?php
class Database
{
    protected $connection = null;
    public function __construct()
    {
        try {
            $this->connection = new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_DATABASE_NAME);
    	
            if ( mysqli_connect_errno()) {
                throw new Exception("Could not connect to database.");   
            }
        } catch (Exception $e) {
            throw new Exception($e->getMessage());   
        }			
    }
    public function select($query = "" , $params = [])
    {
        try {
            $stmt = $this->executeStatement( $query , $params );
            $result = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);				
            $stmt->close();
            return $result;
        } catch(Exception $e) {
            throw New Exception( $e->getMessage() );
        }
        return false;
    }
    private function executeStatement($query = "" , $params = [])
    {
        try {
            $stmt = $this->connection->prepare( $query );
            if($stmt === false) {
                throw New Exception("Unable to do prepared statement: " . $query);
            }
            if( $params ) {
                $stmt->bind_param($params[0], $params[1]);
            }
            $stmt->execute();
            return $stmt;
        } catch(Exception $e) {
            throw New Exception( $e->getMessage() );
        }	
    }
	
	public function insert($username, $user_email, $user_status) {
		
		$sql = "INSERT INTO users (username, user_email, user_status) VALUES (?, ?, ?)";
         
        if($stmt = $this->connection->prepare($sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "sss", $param_username, $param_user_email, $param_user_status);
            
            // Set parameters
            $param_username = $username; 
			$param_user_email = $user_email;
			$param_user_status = $user_status;		
            
            // Attempt to execute the prepared statement
            if($stmt->execute()){
                // Records created successfully. Redirect to landing page
                //header("location: index.php");
                //exit();
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
			$stmt->close();
        }	
	}
	
	public function update($user_id, $username, $user_email, $user_status) {
	
		$sql = "UPDATE users SET username=?, user_email=?, user_status=? WHERE user_id=?";
		 
		if($stmt = $this->connection->prepare($sql)){
			// Bind variables to the prepared statement as parameters
			mysqli_stmt_bind_param($stmt, "sssi", $param_username, $param_user_email, $param_user_status, $param_user_id);
			
			// Set parameters
			$param_username = $username; 
			$param_user_email = $user_email;
			$param_user_status = $user_status;
			$param_user_id = $user_id;
		
			
			// Attempt to execute the prepared statement
			if($stmt->execute()){
				// Records created successfully. Redirect to landing page
				//header("location: index.php");
				//exit();
			} else{
				echo "Oops! Something went wrong. Please try again later.";
			}
			
			$stmt->close();
		}  	
	}
	
	public function deleteBD($id) {
		
		$sql = "DELETE FROM users WHERE user_id=?";
		 
		if($stmt = $this->connection->prepare($sql)){
			// Bind variables to the prepared statement as parameters
			mysqli_stmt_bind_param($stmt, "i", $param_user_id);
			
			// Set parameters
			$param_user_id = $id;		
			
			// Attempt to execute the prepared statement
			if($stmt->execute()){
				// Records created successfully. Redirect to landing page
				//header("location: index.php");
				//exit();
			} else{
				echo "Oops! Something went wrong. Please try again later.11111";
			}
			
			$stmt->close();
		}  		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
?>