data "aws_ami" "pubonline-api-ami" {
  most_recent = true
  owners = ["137112412989"]

  filter {
    name = "name"
    values = ["al2023-ami-2023.5.20240903.0-kernel-6.1-x86_64"]
  }
}