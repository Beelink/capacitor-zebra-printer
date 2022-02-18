const fetchWithTimeout = (request: Request, timeout = 30000): Promise<any> => {
  return Promise.race([
    fetch(request),
    new Promise((_, reject) =>
      setTimeout(() => reject(new Error('timeout')), timeout),
    ),
  ]);
};

export default fetchWithTimeout;
