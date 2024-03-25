import re
import matplotlib.pyplot as plt

# Initialize empty lists to store extracted data
special_characters_count = []
guesses = []

# Open the file containing filtered data for reading
with open('filtered_data_guesses_rockyou.txt', 'r') as file:
    # Read each line
    for line_number, line in enumerate(file, start=1):
        # Extract password and guesses using regular expression
        match = re.match(r"Password: '(.+)', Guesses: Decimal\('(\d+)'\)", line.strip())
        if match:
            password = match.group(1)
            special_character_count = sum(1 for char in password if not char.isalnum())  # Count special characters
            guess = int(match.group(2))
            # Append data to lists
            special_characters_count.append(special_character_count)
            guesses.append(guess)
        else:
            print(f"Warning: Unable to parse line {line_number}: {line.strip()}. Skipping.")

# Check if any data was successfully parsed
if not special_characters_count:
    print("Error: No valid data found in the file.")
    exit()

# Plotting
plt.figure(figsize=(8, 6))
plt.scatter(special_characters_count, guesses, color='blue')
plt.title('RockYou: Password Special Characters Count vs Guesses')
plt.xlabel('Number of Special Characters')
plt.ylabel('Guesses')
plt.grid(True)

# Set y-axis scale to logarithmic
plt.yscale('log')
plt.xlim(0,60)

plt.tight_layout()

# Save the plot as an image file
plt.savefig('password_special_char_guesses_scatter_rockyou_short.png')

# Show the plot
plt.show()
