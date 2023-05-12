import pandas as pd

df1 = pd.read_csv('database\\residents.csv')
df2 = pd.read_csv('database\\stands.csv')


df2 = df2.rename(columns={'Stand Users': 'Name'})
merged_df = pd.merge(df1, df2, on='Name')
merged_df = pd.merge(df1, df2, on='Name', how='outer')
merged_df.to_csv('merged_file.csv', index=False)

