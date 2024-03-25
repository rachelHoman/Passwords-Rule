import re
import matplotlib.pyplot as plt

# Initialize empty lists to store extracted data
numeric_characters_count = []
guesses = []

# Open the file containing filtered data for reading
with open('filtered_data_guesses.txt', 'r') as file:
    # Read each line
    for line_number, line in enumerate(file, start=1):
        # Extract password and guesses using regular expression
        match = re.match(r"Password: '(.+)', Guesses: Decimal\('(\d+)'\)", line.strip())
        if match:
            password = match.group(1)
            numeric_character_count = sum(1 for char in password if char.isdigit())  # Count numeric characters
            guess = int(match.group(2))
            # Append data to lists
            numeric_characters_count.append(numeric_character_count)
            guesses.append(guess)
        else:
            print(f"Warning: Unable to parse line {line_number}: {line.strip()}. Skipping.")

# Check if any data was successfully parsed
if not numeric_characters_count:
    print("Error: No valid data found in the file.")
    exit()

# Plotting
plt.figure(figsize=(8, 6))
plt.scatter(numeric_characters_count, guesses, color='blue')
plt.title('MySpace: Numeric Characters Count vs Guesses')
plt.xlabel('Number of Numeric Characters')
plt.ylabel('Guesses')
plt.grid(True)

# Set y-axis scale to logarithmic
plt.yscale('log')

plt.tight_layout()

# Save the plot as an image file
plt.savefig('password_num_guesses_scatter.png')

# Show the plot
plt.show()
