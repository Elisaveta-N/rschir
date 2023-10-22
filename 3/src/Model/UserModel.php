<?php
require_once PROJECT_ROOT_PATH . "/Model/Database.php";
class UserModel extends Database
{
	/*
    public function getUsers($limit)
    {
        return $this->select("SELECT * FROM users ORDER BY user_id ASC LIMIT ?", ["i", $limit]);
    } */
	
	public function getUsers()
    {
		$sql = "SELECT * FROM users ORDER BY user_id ASC";
        return $this->select($sql, []);
    }
	
	public function getUser($id)
    {
        return $this->select("SELECT * FROM users WHERE user_id = ?", ["i", $id]);
    }	
	
	public function addUser($username, $user_email, $user_status)
    {
		$this->insert($username, $user_email, $user_status);	
    }
	
	public function updateUser($user_id, $username, $user_email, $user_status)
    {
		$this->update($user_id, $username, $user_email, $user_status);	
    }
		
	public function deleteUser($user_id)
    {
		$this->deleteBD($user_id);	
    }
}
?>