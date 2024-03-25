# Open the original file for reading
with open('rockyou_output_pass.txt', 'r') as file:
    # Open a new file for writing
    with open('filtered_data_cracktime_rockyou.txt', 'w') as output_file:
        # Read each line
        for line_number, line in enumerate(file, start=1):
            # Check if the line contains both 'password' and 'crack_times_seconds'
            if "'password'" in line and "'crack_times_seconds'" in line:
                try:
                    # Extract password and crack time seconds from the line
                    password_start = line.find("'password': ")
                    password_end = line.find(",", password_start)
                    password = line[password_start + len("'password': "):password_end]
                    cracktime_start = line.find("'crack_times_seconds': ")
                    cracktime_end = line.find("}", cracktime_start)
                    cracktime = line[cracktime_start + len("'crack_times_seconds': "):cracktime_end]
                    
                    # Write the necessary data to the new file
                    output_file.write(f"Password: {password.strip()}, Crack Times Seconds: {cracktime.strip()}\n")
                except Exception as e:
                    print(f"Warning: Unable to parse line {line_number}: {e}. Skipping.")
                    continue

print("Filtered data saved to 'filtered_data_cracktime_rockyou.txt'")
