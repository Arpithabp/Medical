provider "aws" {
	region = "ap-south-1"
}
resource "aws_instance" "example" {
	ami = "ami-02eb7a4783e7e9317"
	instance_type = "t2.medium"
	vpc_security_group_ids = ["sg-04fba683f37ca92fd"]
	key_name = "AssignmentKey"
	
	connection {
		type = "ssh"
		host = self.public_ip
		user = "ubuntu"
		private_key = file("AssignmentKey.pem")
	}
	
	root_block_device {
      		volume_size = 20
      		volume_type = "gp2"
   	}
	
     	tags = {
        	Name = "Test-server"
  	}
}
