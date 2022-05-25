export function getAvatarInitials(name: string): string {
  return name
    .split(' ')
    .slice(0, 2)
    .map((word) => {
      return word.charAt(0).toUpperCase();
    })
    .join('')
    .toUpperCase();
}
