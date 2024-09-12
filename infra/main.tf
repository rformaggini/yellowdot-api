resource "aws_vpc" "pubonline-api-vpc" {
  cidr_block = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support = true

  tags = {
    "name" = "pubonline-api-vpc"
  }
}

resource "aws_subnet" "pubonline-api-subnet" {
  vpc_id = "${aws_vpc.pubonline-api-vpc.id}"
  cidr_block = "10.0.1.0/24"
  availability_zone = "us-east-1a"
  map_public_ip_on_launch = true
  tags = {
    "name" = "pubonline-api-subnet-pub"
  }
}

resource "aws_internet_gateway" "pubonline-api-gateway" {
  vpc_id = "${aws_vpc.pubonline-api-vpc.id}"
  tags = {
    "name" = "pubonline-api-gateway"
  }
}

resource "aws_route_table" "pubonline-api-route-table" {
  vpc_id = "${aws_vpc.pubonline-api-vpc.id}"
  tags = {
    "name" = "pubonline-api-route-table"
  }
}

resource "aws_route" "pubonline-api-route" {
  route_table_id = "${aws_route_table.pubonline-api-route-table.id}"
  destination_cidr_block = "0.0.0.0/0"
  gateway_id = "${aws_internet_gateway.pubonline-api-gateway.id}"
}

resource "aws_route_table_association" "pubonline-api-route-table-assoc" {
  route_table_id = "${aws_route_table.pubonline-api-route-table.id}"
  subnet_id = "${aws_subnet.pubonline-api-subnet.id}"
}

resource "aws_instance" "pubonline-api-ec2" {
  instance_type = "t2.micro"
  key_name = "${aws_key_pair.pubonline-api-key.id}"
  vpc_security_group_ids = [aws_security_group.pubonline-api-sg.id]
  subnet_id = "${aws_subnet.pubonline-api-subnet.id}"

  ami = "${data.aws_ami.pubonline-api-ami.id}"

  user_data = file("userdata.tpl")

  root_block_device {
    volume_size = 8
  }

  tags = {
    "name" = "pubonline-api-ec2"
  }

}