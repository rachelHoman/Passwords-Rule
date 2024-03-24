# Open the original file for reading
with open('myspace_output_pass.txt', 'r') as file:
    # Open a new file for writing
    with open('filtered_data_score.txt', 'w') as output_file:
        # Read each line
        for line_number, line in enumerate(file, start=1):
            # Parse the line to extract relevant data
            if 'password' in line and 'score' in line:
                try:
                    # Extract password and score from the line
                    password = line.split("'password': '")[1].split("'")[0]
                    score = line.split("'score': ")[1].split(',')[0]
                    # Write the necessary data to the new file
                    output_file.write(f"Password: {password}, Score: {score}\n")
                except IndexError:
                    print(f"Warning: Unable to parse line {line_number}. Skipping.")
                    continue

print("Filtered data saved to 'filtered_data_score.txt'")
