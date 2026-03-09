const EMAIL_REGEX = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

export const checkInvalidateEmail = (email:string) => !EMAIL_REGEX.test(email);

export const checkInvalidatePassword = (password: string) => password.length < 6;