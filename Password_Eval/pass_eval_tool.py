from zxcvbn import zxcvbn

# Read passwords from the file
# passwords_file = "myspace-withcount.txt"
passwords_file = "rockyou-withcount.txt"
passwords = []

with open(passwords_file, 'r', encoding='latin-1') as file:  # Try a different encoding
    for line in file:
        data = line.strip().split(' ', 1)  # Split count and password
        if len(data) == 2:
            count, password = data
            passwords.extend([password] * int(count))  # Repeat password count times

# Open a new file to write the output
output_file = "output_pass.txt"
with open(output_file, 'w') as output:
    # Now iterate over passwords
    for password in passwords:
        results = zxcvbn(password)
        output.write(str(results) + '\n')
