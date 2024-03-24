import re
import matplotlib.pyplot as plt
from decimal import Decimal

# Define regular expressions to extract data
password_pattern = re.compile(r"Password: '(\w+)'")
crack_times_pattern = re.compile(r"'(\w+)': Decimal\('(.+?)'\)")

# Initialize lists to store extracted data
password_lengths = []
online_throttling = []
online_no_throttling = []
offline_slow_hashing = []
offline_fast_hashing = []

# Open the file and extract data
with open("filtered_data/filtered_data_cracktime.txt", "r") as file:
    for line in file:
        # Extract password and crack time data
        password_match = password_pattern.search(line)
        crack_times_match = crack_times_pattern.findall(line)
        if password_match and crack_times_match:
            password = password_match.group(1)
            password_lengths.append(len(password))
            for match in crack_times_match:
                time = Decimal(match[1])  # Use Decimal to handle scientific notation
                if match[0] == 'online_throttling_100_per_hour':
                    online_throttling.append(float(time))
                elif match[0] == 'online_no_throttling_10_per_second':
                    online_no_throttling.append(float(time))
                elif match[0] == 'offline_slow_hashing_1e4_per_second':
                    offline_slow_hashing.append(float(time))
                elif match[0] == 'offline_fast_hashing_1e10_per_second':
                    offline_fast_hashing.append(float(time))

# Plot the data as a scatter plot
plt.figure(figsize=(10, 6))
plt.scatter(password_lengths, online_throttling, label='online_throttling_100_per_hour', alpha=0.5)
plt.scatter(password_lengths, online_no_throttling, label='online_no_throttling_10_per_second', alpha=0.5)
plt.scatter(password_lengths, offline_slow_hashing, label='offline_slow_hashing_1e4_per_second', alpha=0.5)
plt.scatter(password_lengths, offline_fast_hashing, label='offline_fast_hashing_1e10_per_second', alpha=0.5)
plt.xlabel('Password Length')
plt.ylabel('Crack Time')
plt.title('Crack Time vs. Password Length (Scatter Plot)')
plt.legend()
plt.grid(True)

# Save the plot as an image file
plt.savefig('password_length_cracktime_scatter.png')

plt.show()
