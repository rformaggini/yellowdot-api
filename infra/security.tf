resource "aws_security_group" "pubonline-api-sg" {
  name = "pubonline-api-sg"
  description = "Security Group for Pubonline Api"
  vpc_id = "${aws_vpc.pubonline-api-vpc.id}"
}

resource "aws_security_group_rule" "sg-rules-pub-out" {
  from_port         = 0
  protocol          = "-1"
  security_group_id = "${aws_security_group.pubonline-api-sg.id}"
  to_port           = 0
  type              = "egress"
  cidr_blocks = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "sg-rules-ssh-in" {
  from_port         = 22
  protocol          = "tcp"
  security_group_id = "${aws_security_group.pubonline-api-sg.id}"
  to_port           = 22
  type              = "ingress"
  cidr_blocks = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "sg-rules-http-in" {
  from_port         = 0
  protocol          = "tcp"
  security_group_id = "${aws_security_group.pubonline-api-sg.id}"
  to_port           = 0
  type              = "ingress"
  cidr_blocks = ["0.0.0.0/0"]
}

resource "aws_key_pair" "pubonline-api-key" {
  key_name = "pubonline-api-key"
  public_key = file("~/.ssh/id_rsa.pub")
}